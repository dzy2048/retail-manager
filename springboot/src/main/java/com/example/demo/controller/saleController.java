package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.*;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.mapper.GoodMapper;
import com.example.demo.mapper.OrderItemMapper;
import com.example.demo.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sale")
public class saleController {
    @Resource
    GoodMapper goodMapper;
    @Resource
    CustomerMapper customerMapper;
    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderItemMapper orderItemMapper;
    @GetMapping("/findgood")
    public Result<?> findgood(@RequestParam String search)
    {
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.eq("goodname",search);
        List<Good> goods = goodMapper.selectList(wrapper);
        return Result.success(goods);
    }
    @GetMapping("/findcustomer")
    public Result<?> findcustomer(@RequestParam String search)
    {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("customername",search);
        Customer customer = customerMapper.selectOne(wrapper);
        return Result.success(customer);
    }
    @PostMapping("/submit")
    public Result<?> submit(@RequestBody OrderSubmit orderSubmit)
    {
        Order order = new Order();
        order.setOrderperiod(orderSubmit.getOrderperiod());
        order.setCustomerid(orderSubmit.getCustomerid());
        order.setTotalprice(order.getTotalprice());
        orderMapper.insert(order);
        for(OrderItem orderItem : orderSubmit.getOrderitems())
        {
            orderItemMapper.insert(orderItem);
        }
        return Result.success();
    }
}
