package com.example.demo.controllers;

import com.example.demo.models.WarehouseFirst;
import com.example.demo.service.impls.WarehouseFirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/warehouse1")
public class WarehouseFirstControllers {
    private final WarehouseFirstService warehouseFirstService;

    @Autowired
    public WarehouseFirstControllers(WarehouseFirstService warehouseFirstService) {
        this.warehouseFirstService = warehouseFirstService;
    }

    @GetMapping
    public List<WarehouseFirst> getAllWarehouseFirst() {
        return this.warehouseFirstService.findAll();
    }
}
