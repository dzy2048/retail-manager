package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSubmit {
    @TableId(type = IdType.AUTO)
    private String orderperiod;
    private Integer userid;//可删除？
    private Integer customerid;
    private Double totalprice;
    private List<OrderItem> orderitems= new ArrayList<>();
}
