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

    @GetMapping("/getorder")
    public Result<?> getOrders(@RequestParam String searchByid)
    {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("customerid",searchByid);
        List<Order> orders = orderMapper.selectList(wrapper);
        return Result.success(orders);
    }
    @PutMapping("/pay")
    public Result<?> pay(@RequestParam String state,@RequestParam Integer orderid)
    {
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderid",orderid).set("state", state);
        orderMapper.update(null, updateWrapper);
        return Result.success();

    }

}
