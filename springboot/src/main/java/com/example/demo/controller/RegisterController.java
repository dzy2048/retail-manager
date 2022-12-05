package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JsonUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    @CrossOrigin
    public String register(@RequestBody Map<String,String> form)
    {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String username = form.get("username");
        wrapper.eq("username",username);
        User user = userService.getOne(wrapper);

        Map<String,String> status = new HashMap<>();
        if(user != null)
        {
            status.put("status","forbid");
        }
        else
        {
            User newUser = new User(username,form.get("password"),Integer.parseInt(form.get("authority")));
            System.out.println(newUser);
            userService.save(newUser);
            status.put("status","ok");
        }
        String result = JsonUtils.getJson(status);
        return result;
    }
}
