package com.example.demo.repo;

import com.example.demo.models.WarehouseFirst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseFirstRepo extends JpaRepository<WarehouseFirst, Integer> {
    List<WarehouseFirst> findByGoodsId(int goodsId);
}
