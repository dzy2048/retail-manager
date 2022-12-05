package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.Good;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.GoodService;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private OrderItemService itemService;
    @Resource
    private GoodService goodService;

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

    @GetMapping("/detail")
    @CrossOrigin
    public Result<?> detail(@RequestParam int orderId)
    {
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> details = new ArrayList<>();

        Order order = orderService.getById(orderId);
        QueryWrapper<OrderItem> wrapper_item = new QueryWrapper<>();
        wrapper_item.eq("order_id",orderId);
        List<OrderItem> items = itemService.list(wrapper_item);
        for (OrderItem item : items)
        {
            Good good = goodService.getById(item.getGoodId());
            Map<String,Object> detail = new HashMap<>();
            detail.put("goodName",good.getGoodName());
            detail.put("wholePrice",good.getWholePrice());
            detail.put("number",item.getGoodNumber());
            details.add(detail);
        }

        map.put("orderData",details);
        map.put("totalPrice",order.getShouldPay());
        return Result.success(map);
    }

    @PutMapping("/change")
    @CrossOrigin
    public Result<?> change(@RequestBody Map<String,Object> map)
    {
        Integer id = (Integer) map.get("id");
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",id);
        Order order = orderService.getOne(wrapper);
        double paid = Double.parseDouble((String) map.get("paid"));
        if (paid == order.getShouldPay())
            order.setState("已完成");
        order.setHavePaid(paid);
        orderService.update(order,wrapper);
        return Result.success();
    }
}
