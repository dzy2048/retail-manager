package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    private Integer userId;
    private Date time;
    private Integer customerId;
    private Double shouldPay;
    private Double havePaid;
    private String state;
    private Double profit;

}
