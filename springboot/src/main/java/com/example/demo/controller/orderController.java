package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class orderController {
    @Resource
    OrderMapper orderMapper;

    @GetMapping("/all")
    @CrossOrigin
    public Result<?> getall()
    {
      List<Order> orders = orderMapper.selectList(null);
      return Result.success(orders);
    }
    @GetMapping("/byid")
    @CrossOrigin
    public Result<?> getById(@RequestParam Integer id)
    {
      QueryWrapper<Order> wrapper = new QueryWrapper<>();
      wrapper.eq("order_id",id);
      Order order = orderMapper.selectOne(wrapper);
      return Result.success(order);
    }



}
