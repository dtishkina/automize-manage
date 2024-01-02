package com.example.demo.repo;

import com.example.demo.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepo extends JpaRepository<Sales, Integer> {
    int countByGoodId(int goodId);

}
