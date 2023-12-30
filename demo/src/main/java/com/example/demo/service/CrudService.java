package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface CrudService <T> {
    void delete(T elem);

    void save(T elem);

    Optional<T> findById(int id);

    List<T> findAll();
}
