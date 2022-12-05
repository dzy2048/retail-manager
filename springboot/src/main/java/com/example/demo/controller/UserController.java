package com.example.demo.controller;

import com.example.demo.commom.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @PostMapping
    public Result<?> save(@RequestBody User user)
    {
        userMapper.insert(user);
        return Result.success();
    }
    @PutMapping
    public Result<?> update(@RequestBody User user)
    {
        userMapper.updateById(user);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id)
    {
        userMapper.deleteById(id);
        return Result.success();
    }


//    @GetMapping
//    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
//                              @RequestParam(defaultValue = "10") Integer pageSize,
//                              @RequestParam(defaultValue = "") String search)
//    {
//        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
//        if(StrUtil.isNotBlank(search))
//        {
//            wrapper.like(User::getNickname,search);
//        }
//        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
//        return Result.success(userPage);
//    }
}
