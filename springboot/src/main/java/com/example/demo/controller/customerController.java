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
@RequestMapping("/customer")
public class customerController {
    @Resource
    CustomerMapper customerMapper;

    @GetMapping("/all")
    @CrossOrigin
    public String getCustomers() {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        List<Customer> customers = customerMapper.selectList(null);
        String result = "{\"tableData\":" + JsonUtils.getJson(customers) + "}";
        return result;
    }

    @GetMapping("/find")
    @CrossOrigin
    public Result<?> getByName(@RequestParam String username) {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        List<Customer> customers = customerMapper.selectList(wrapper);
        return Result.success(customers);
    }

    @PostMapping("/add")
    @CrossOrigin
    public Result<?> insert(@RequestBody Customer customer) {
        customerMapper.insert(customer);
        return Result.success();
    }

    @DeleteMapping("/delete")
    @CrossOrigin
    public Result<?> delete(@RequestParam Integer id) {
        customerMapper.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    @CrossOrigin
    public Result<?> update(@RequestBody Customer customer) {
        customerMapper.updateById(customer);
        return Result.success();
    }

}
