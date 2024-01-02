package com.example.demo.controllers;

import com.example.demo.models.WarehouseSecond;
import com.example.demo.service.impls.WarehouseSecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/warehouse2")
public class WarehouseSecondControllers {
    private final WarehouseSecondService warehouseService;

    @Autowired
    public WarehouseSecondControllers(WarehouseSecondService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<WarehouseSecond> findAll(){
        return warehouseService.findAll();
    }
}
