package com.example.demo.service.impls;

import com.example.demo.models.Goods;
import com.example.demo.repo.GoodsRepo;
import com.example.demo.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsService implements CrudService <Goods>{
    private final GoodsRepo goodsRepo;

    @Autowired
    public GoodsService(GoodsRepo goodsRepo){
        this.goodsRepo = goodsRepo;
    }

    @Override
    public void delete(Goods elem) {
        goodsRepo.delete(elem);
    }

    @Override
    public void save(Goods elem) {
        goodsRepo.save(elem);
    }

    @Override
    public Optional<Goods> findById(int id) {
        return goodsRepo.findById(id);
    }

    @Override
    public List<Goods> findAll() {
        return goodsRepo.findAll();
    }
}
