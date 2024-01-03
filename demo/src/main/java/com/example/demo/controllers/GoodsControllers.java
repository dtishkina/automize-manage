package com.example.demo.controllers;

import com.example.demo.dto.GoodsDTO;
import com.example.demo.models.Goods;
import com.example.demo.service.impls.GoodsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGood(@PathVariable int id) {
        System.out.println(goodsService.findById(id));
        Optional<Goods> goodOptional = goodsService.findById(id);
        if (goodOptional.isPresent()) {
            goodsService.delete(goodOptional.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
