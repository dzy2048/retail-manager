package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.OrderItem;
import com.example.demo.mapper.OrderItemMapper;
import com.example.demo.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
}
