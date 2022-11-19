package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/register")
public class registerController {
    @Resource
    UserMapper userMapper;
    @PostMapping
    public Result<?> register(@RequestBody User userP)
//注册要审核吗
    {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        System.out.println(userP.getUsername());
        wrapper.eq("username",userP.getUsername());
        User user = userMapper.selectOne(wrapper);
        if(user != null)
        {
            return Result.error("1","用户名已存在");
        }
        else
        {
            userMapper.insert(userP);
            return Result.success();
        }
    }
}
