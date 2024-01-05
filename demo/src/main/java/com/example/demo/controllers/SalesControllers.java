package com.example.demo.controllers;

import com.example.demo.models.Sales;
import com.example.demo.service.impls.SalesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/{goodId}/demand")
    public List<Sales> getSalesForGood(
            @PathVariable int goodId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return salesService.getSalesByGoodAndDateRange(goodId, startDate, endDate);
    }
}
