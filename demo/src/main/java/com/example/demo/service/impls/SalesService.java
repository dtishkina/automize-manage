package com.example.demo.service.impls;

import com.example.demo.models.Sales;
import com.example.demo.repo.SalesRepo;
import com.example.demo.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesService implements CrudService<Sales> {
    private final SalesRepo salesRepo;

    @Autowired
    public SalesService(SalesRepo salesRepo) {
        this.salesRepo = salesRepo;
    }

    @Override
    public void delete(Sales elem) {
        salesRepo.delete(elem);
    }

    @Override
    public void save(Sales elem) {
        salesRepo.save(elem);
    }

    @Override
    public Optional<Sales> findById(int id) {
        return salesRepo.findById(id);
    }

    @Override
    public List<Sales> findAll() {
        return salesRepo.findAll();
    }
}
