package com.example.demo.repo;

import com.example.demo.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalesRepo extends JpaRepository<Sales, Integer> {
    int countByGoodId(int goodId);

    List<Sales> findByGoodIdAndCreateDateBetween(int goodId, LocalDate startDate, LocalDate endDate);
}
