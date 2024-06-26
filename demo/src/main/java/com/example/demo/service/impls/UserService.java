package com.example.demo.service.impls;

import com.example.demo.models.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements CrudService<User>{

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public void delete(User elem) {
        this.userRepo.delete(elem);
    }

    @Override
    public void save(User elem) {
        this.userRepo.save(elem);
    }

    @Override
    public Optional<User> findById(int id) {
        return this.userRepo.findById(id);
    }

    @Override
    public List<User> findAll() {
        return this.userRepo.findAll();
    }
}
