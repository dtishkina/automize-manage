package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Supplier {

    public static void main(String[] args) {
        System.out.println((new BCryptPasswordEncoder(12).encode("admin")));

    }
}