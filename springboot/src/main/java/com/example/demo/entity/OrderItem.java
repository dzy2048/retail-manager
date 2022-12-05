package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("order_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @TableId(type = IdType.AUTO)
    private Integer itemId;
    private Integer orderId;
    private Integer goodId;
    private Double goodPrice;
    private Integer goodNumber;
}
