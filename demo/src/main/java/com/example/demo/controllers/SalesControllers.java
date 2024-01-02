package com.example.demo.controllers;

import com.example.demo.models.Sales;
import com.example.demo.service.impls.SalesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/sales")
public class SalesControllers {
    private final SalesService salesService;

    public SalesControllers(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    public List<Sales> getAllSales(){
        return salesService.findAll();
    }
}
