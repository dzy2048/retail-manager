package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.Good;
import com.example.demo.service.GoodService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/good")
public class GoodController
{
    @Resource
    private GoodService service;

    @GetMapping("/all")
    @CrossOrigin
    public Result<?> getGoodList()
    {
        List<Good> goodList = service.list();
        return Result.success(goodList);
    }

    @GetMapping("/query")
    @CrossOrigin
    public Result<?> queryGood(@RequestParam String name)
    {
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.like("good_name",name);
        List<Good> goodList = service.list(wrapper);
        return Result.success(goodList);
    }

    @PostMapping("/add")
    @CrossOrigin
    public Result<?> addGood(@RequestBody Good good)
    {
        String name = good.getGoodName();
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.eq("good_name",name);
        List<Good> goodList = service.list(wrapper);
        if (goodList.size() == 0)
        {
            service.save(good);
        }
        return Result.success();
    }

    @PutMapping("/update")
    @CrossOrigin
    public Result<?> updateGood(@RequestBody Good good)
    {
        service.updateById(good);
        return Result.success();
    }

    @DeleteMapping("/delete")
    @CrossOrigin
    public Result<?> deleteGood(@RequestBody Map<String,Integer> map)
    {
        Integer id = map.get("id");
        System.out.println(id);
        service.removeById(id);
        return Result.success();
    }
}
