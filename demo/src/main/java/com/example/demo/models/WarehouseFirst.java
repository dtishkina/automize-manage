package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "warehouse1", schema = "lab1")
public class WarehouseFirst {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "good_id")
    private int goodsId;

    @Column(name = "good_count")
    private int goodCountFirst;
}
