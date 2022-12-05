package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem
{
    private Integer goodId;
    private String goodName;
    private Double price;
    private int number;
    private String unit;
    private int repoId;
}
