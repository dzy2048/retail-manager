package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @TableId(type = IdType.AUTO)//主键自增
    private Integer customer_id;
    private String name;
    private String phone;
    private String address;
}
