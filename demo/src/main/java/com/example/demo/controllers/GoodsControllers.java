package com.example.demo.controllers;

import com.example.demo.dto.GoodsDTO;
import com.example.demo.models.Goods;
import com.example.demo.service.impls.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/goods")
public class GoodsControllers {
    private final GoodsService goodsService;

    public GoodsControllers(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping
    public List<Goods> getAllGoods() {
        return goodsService.findAll();
    }

    @GetMapping("/top5")
    public List<GoodsDTO> getAllGoodsWithSales() {
        return goodsService.getAllGoodsWithSalesCount();
    }

    @GetMapping("/all_with_warehouse")
    public List<GoodsDTO> getAllGoodsWithWarehouseCounts() {
        return goodsService.getAllGoodsWithWarehouseCounts();
    }
}
