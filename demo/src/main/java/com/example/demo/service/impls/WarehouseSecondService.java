package com.example.demo.service.impls;

import com.example.demo.models.WarehouseSecond;
import com.example.demo.repo.WarehouseSecondRepo;
import com.example.demo.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseSecondService implements CrudService<WarehouseSecond> {
    private final WarehouseSecondRepo warehouseSecondRepo;

    @Autowired
    public WarehouseSecondService(WarehouseSecondRepo warehouseSecondRepo) {
        this.warehouseSecondRepo = warehouseSecondRepo;
    }

    @Override
    public void delete(WarehouseSecond elem) {
        warehouseSecondRepo.delete(elem);
    }

    @Override
    public void save(WarehouseSecond elem) {
        warehouseSecondRepo.save(elem);
    }

    @Override
    public Optional<WarehouseSecond> findById(int id) {
        return warehouseSecondRepo.findById(id);
    }

    @Override
    public List<WarehouseSecond> findAll() {
        return warehouseSecondRepo.findAll();
    }
}
