package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Good;
import com.example.demo.mapper.GoodMapper;
import com.example.demo.service.GoodService;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {
}
