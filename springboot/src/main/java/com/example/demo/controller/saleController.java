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
import java.util.Map;

@RestController
@RequestMapping("/index/sales")
public class saleController {
    @Resource
    GoodMapper goodMapper;
    @Resource
    CustomerMapper customerMapper;
    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderItemMapper orderItemMapper;
    @GetMapping()
    @CrossOrigin
    public Result<?> selectall()
    {

      List<Good> goods = goodMapper.selectList(null);
      return Result.success(goods);
    }
    @GetMapping("/findgood")
    @CrossOrigin
    public Result<?> findGood(@RequestParam String goodName)
    {
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.eq("good_name",goodName);
        List<Good> goods = goodMapper.selectList(wrapper);
        return Result.success(goods);
    }
//    @PostMapping("/submit")
//    public Result<?> submit(@RequestBody OrderSubmit orderSubmit)
//    {
//        Order order = new Order();
//        order.setTime(orderSubmit.getOrderperiod());
//        order.setcustomerId(orderSubmit.getCustomerid());
//        order.setTotalprice(order.getTotalprice());
//        orderMapper.insert(order);
//        for(OrderItem orderItem : orderSubmit.getOrderitems())
//        {
//            orderItemMapper.insert(orderItem);
//        }
//        return Result.success();
//    }
}
