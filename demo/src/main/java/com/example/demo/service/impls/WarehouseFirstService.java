package com.example.demo.service.impls;

import com.example.demo.models.WarehouseFirst;
import com.example.demo.repo.WarehouseFirstRepo;
import com.example.demo.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseFirstService implements CrudService<WarehouseFirst> {
    private final WarehouseFirstRepo warehouseFirstRepo;

    @Autowired
    public WarehouseFirstService(WarehouseFirstRepo warehouseFirstRepo) {
        this.warehouseFirstRepo = warehouseFirstRepo;
    }
    @Override
    public void delete(WarehouseFirst elem) {
        warehouseFirstRepo.delete(elem);
    }

    @Override
    public void save(WarehouseFirst elem) {
        warehouseFirstRepo.save(elem);

    }

    @Override
    public Optional<WarehouseFirst> findById(int id) {
        return warehouseFirstRepo.findById(id);
    }

    @Override
    public List<WarehouseFirst> findAll() {
        return warehouseFirstRepo.findAll();
    }
}
