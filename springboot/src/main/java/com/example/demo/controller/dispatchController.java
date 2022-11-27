package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.Good;
import com.example.demo.mapper.GoodMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index/dispatch")
public class dispatchController {
    @Resource
    GoodMapper goodMapper;

    @GetMapping("/all")
    @CrossOrigin
    public Result<?> getAll(@RequestParam Integer id)
    {
      QueryWrapper<Good> wrapper = new QueryWrapper<>();
      wrapper.eq("repository",id);
      List<Good> goods = goodMapper.selectList(wrapper);
      return Result.success(goods);
    }

    @PostMapping("/move")
    @CrossOrigin
    public Result<?> move(@RequestBody Map<String,Object> map)
    {
        Integer t_repo = Integer.parseInt((String)map.get("target_id"));
        List<Integer> list = (List)map.get("moveList");
        System.out.println(map);
        for(Integer id : list)
        {
            QueryWrapper<Good> wrapper = new QueryWrapper<>();
            wrapper.eq("good_id",(id+1));
            Good good = goodMapper.selectOne(wrapper);
            goodMapper.updateById(good);
        }
        return Result.success();
    }

}
