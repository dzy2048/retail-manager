package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/all")
    @CrossOrigin
    public Result<?> getAll()
    {
        List<Order> orders = orderService.list();
        return Result.success(orders);
    }

    @GetMapping("/query")
    @CrossOrigin
    public Result<?> getById(@RequestParam Integer id)
    {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", id);
        List<Order> order = orderService.list(wrapper);
        return Result.success(order);
    }

    @PutMapping("/refund")
    @CrossOrigin
    public Result<?> refund(@RequestBody Map<String,Integer> map)
    {
        Integer id = map.get("id");
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",id);
        Order order = new Order();
        order.setState("已退款");
        orderService.update(order,wrapper);
        return Result.success();
    }

    @PutMapping("/finish")
    @CrossOrigin
    public Result<?> finish(@RequestBody Map<String,Integer> map)
    {
        Integer id = map.get("id");
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",id);
        Order order = new Order();
        order.setState("已完成");
        orderService.update(order,wrapper);
        return Result.success();
    }

    @PutMapping("/change")
    @CrossOrigin
    public Result<?> change(@RequestBody Map<String,Object> map)
    {
        Integer id = (Integer) map.get("id");
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",id);
        Order order = new Order();
        order.setHavePaid(Double.parseDouble((String) map.get("paid")));
        orderService.update(order,wrapper);
        return Result.success();
    }
}
