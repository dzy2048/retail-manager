package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.JsonUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Resource
    UserMapper userMapper;

    @PostMapping("/login")
    @CrossOrigin
    public String check(@RequestBody Map<String,String> form)
    {
//        String username = "";
//        String password = "";
      String username = form.get("username");
      String password = form.get("password");

//      System.out.println(form);
      System.out.println(username+"\n"+password);

      QueryWrapper<User> wrapper = new QueryWrapper<>();

      wrapper.eq("username",username);
      User user = userMapper.selectOne(wrapper);

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
