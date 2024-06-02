package com.example.demo.repo;

import com.example.demo.models.WarehouseSecond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseSecondRepo extends JpaRepository<WarehouseSecond, Integer> {
    List<WarehouseSecond> findByGoodsId(int goodsId);
}
