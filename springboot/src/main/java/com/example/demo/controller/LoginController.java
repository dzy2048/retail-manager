package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.ClientUser;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    @CrossOrigin
    public Result<?> check(@RequestBody Map<String, String> form) {
        String username = form.get("username");
        String password = form.get("password");


        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("username", username);
        User user = userService.getOne(wrapper);

        Map<String, String> status = new HashMap<>();

        if (user == null) {
            System.out.println("用户不存在");
            return Result.error("1","用户不存在");
        }
        if (user.getPassword().equals(password)) {
            ClientUser clientUser = new ClientUser(username,user.getAuthority());
            return Result.success(clientUser);
        } else {
            return Result.error("1","密码错误");
        }

    }
}
