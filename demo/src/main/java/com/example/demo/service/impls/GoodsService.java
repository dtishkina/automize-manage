package com.example.demo.service.impls;

import com.example.demo.dto.GoodsDTO;
import com.example.demo.models.Goods;
import com.example.demo.models.WarehouseFirst;
import com.example.demo.models.WarehouseSecond;
import com.example.demo.repo.GoodsRepo;
import com.example.demo.repo.SalesRepo;
import com.example.demo.repo.WarehouseFirstRepo;
import com.example.demo.repo.WarehouseSecondRepo;
import com.example.demo.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoodsService implements CrudService <Goods>{
    private final GoodsRepo goodsRepo;
    private final SalesRepo salesRepo;

    private final WarehouseFirstRepo warehouseFirstRepo;

    private final WarehouseSecondRepo warehouseSecondRepo;

    @Autowired
    public GoodsService(GoodsRepo goodsRepo, SalesRepo salesRepo,
                        WarehouseFirstRepo warehouseFirstRepo,
                        WarehouseSecondRepo warehouseSecondRepo) {
        this.goodsRepo = goodsRepo;
        this.salesRepo = salesRepo;
        this.warehouseFirstRepo = warehouseFirstRepo;
        this.warehouseSecondRepo = warehouseSecondRepo;
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

    public List<GoodsDTO> getAllGoodsWithWarehouseCounts() {
        List<Goods> goods = goodsRepo.findAll();
        return goods.stream().map(this::mapWithWarehouseToDTO).collect(Collectors.toList());
    }

    public List<GoodsDTO> getAllGoodsWithSalesCount() {
        List<Goods> goods = goodsRepo.findAll();
        return goods.stream()
                .map(this::mapToDTO)
                .sorted(Comparator.comparing(GoodsDTO::getNumberOfSales).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    private GoodsDTO mapToDTO(Goods good) {
        GoodsDTO dto = new GoodsDTO();
        dto.setId(good.getId());
        dto.setName(good.getName());
        dto.setPriority(good.getPriority());
        dto.setNumberOfSales(salesRepo.countByGoodId(good.getId()));
        return dto;
    }

    private GoodsDTO mapWithWarehouseToDTO(Goods good) {
        GoodsDTO dto = new GoodsDTO();
        dto.setId(good.getId());
        dto.setName(good.getName());
        dto.setPriority(good.getPriority());
        dto.setGoodCountFirst(warehouseFirstRepo.findByGoodsId(good.getId())
                .map(WarehouseFirst::getGoodCountFirst).orElse(0));
        dto.setGoodCountSecond(warehouseSecondRepo.findByGoodsId(good.getId())
                .map(WarehouseSecond::getGoodCountSecond).orElse(0));

        return dto;
    }

}
