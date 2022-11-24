package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    private String time;
    private Integer userId;
    private Integer customerId;
    private Double price;
    private String state;
    private Double profit;
}
