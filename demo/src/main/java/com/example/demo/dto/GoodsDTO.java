package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoodsDTO {
    private int id;
    private String name;
    private int priority;
    private int numberOfSales;
    private int goodCountFirst;
    private int goodCountSecond;
}