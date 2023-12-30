package com.example.demo.repo;

import com.example.demo.models.WarehouseFirst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseFirstRepo extends JpaRepository<WarehouseFirst, Integer> {
}
