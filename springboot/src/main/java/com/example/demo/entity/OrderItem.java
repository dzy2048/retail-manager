package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("orderitem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @TableId(type = IdType.AUTO)
    private Integer orderitemid;
    private Integer orderid;
    private Integer goodid;
    private Double goodprice;
    private Integer quantity;
}
