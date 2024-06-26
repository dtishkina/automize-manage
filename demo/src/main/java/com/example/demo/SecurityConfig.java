package com.example.demo;

import com.example.demo.models.User;
import com.example.demo.service.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.DELETE, "/rest/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/rest/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/rest/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/rest/**").hasAnyRole("ADMIN", "OBSERVER")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("http://127.0.0.1:9000/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .httpBasic();

        return http.build();
    }


    @Bean
    protected UserDetailsService userDetailsService(@Autowired UserService userService) {
        List<UserDetails> springSecurityUserList = new ArrayList<>();
        List<User> userList = userService.findAll();
        for (User user : userList) {
            springSecurityUserList.add(org.springframework.security.core.userdetails.User.builder()
                    .username(user.getLogin())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build()
            );
        }
        return new InMemoryUserDetailsManager(
                springSecurityUserList
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
