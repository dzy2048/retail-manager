package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController
{
    @Resource
    private UserService userService;

    @PostMapping("/login")
    @CrossOrigin
    public String check(@RequestBody Map<String,String> form)
    {
        String username = form.get("username");
        String password = form.get("password");

        System.out.println(username+"\n"+password);

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("username",username);
        User user = userService.getOne(wrapper);

        Map<String,String> status = new HashMap<>();
        String result = null;

        if(user == null)
        {
          System.out.println("用户不存在");
          status.put("status","forbid");
          result = JsonUtils.getJson(status);
          return result;
        }
        if (user.getPassword().equals(password))
        {
          status.put("status","ok");
        }
        else
        {
          status.put("status","forbid");
        }

        result = JsonUtils.getJson(status);
        return result;
    }
}
