package com.example.demo.controller;

import com.example.demo.commom.Result;
import com.example.demo.entity.Good;
import com.example.demo.service.GoodService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GoodController
{
    @Resource
    private GoodService service;

    @GetMapping("/good")
    @CrossOrigin
    public Result<?> getGood()
    {
        List<Good> goodList = service.list();
        return Result.success(goodList);
    }
}
