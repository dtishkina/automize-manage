package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sales", schema = "lab1")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(name = "good_id")
    private int goodsId;

    @Column(name = "good_count")
    private int goodCount;

    @Column(name = "create_date")
    private LocalDate createDate;
}
