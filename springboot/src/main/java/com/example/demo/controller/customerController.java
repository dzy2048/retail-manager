package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class customerController {
    @Resource
    CustomerMapper customerMapper;
    @GetMapping("/asda")
    public Result<?> getcustomers(@RequestParam(defaultValue = "") String customername)
    {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("customername",customername);
        List<Customer> customers = customerMapper.selectList(wrapper);
        return Result.success(customers);
    }

    @GetMapping("/customer")
    @CrossOrigin
    public String getCustomers()
    {
      QueryWrapper<Customer> wrapper = new QueryWrapper<>();
      List<Customer> customers = customerMapper.selectList(null);
      String result = "{\"tableData\":" + JsonUtils.getJson(customers) + "}";
      System.out.println("there is a request");
      return result;
    }
}
