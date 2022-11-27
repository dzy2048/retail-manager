package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Good;
import com.example.demo.mapper.GoodMapper;
import com.example.demo.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index/repo")
public class RepositoryController {
    @Resource
    GoodMapper goodMapper;
    @GetMapping("/all")
    @CrossOrigin
    public Result<?> getGood()
    {
      List<Good> goods = goodMapper.selectList(null);
      return Result.success(goods);
    }

    @GetMapping("/find")
    @CrossOrigin
    public Result<?> getByName(@RequestParam String goodName)
    {
      QueryWrapper<Good> wrapper = new QueryWrapper<>();
      wrapper.eq("good_name",goodName);
      List<Good> goods = goodMapper.selectList(wrapper);
      return Result.success(goods);
    }
    @PutMapping("/updateprice")
    @CrossOrigin
    public Result<?> updatePrice(@RequestBody Map<String,Object> map)
    {
      Integer id = (Integer) map.get("id");
      Double wholePrice = Double.parseDouble((String) map.get("wholePrice"));
      Double sellPrice = Double.parseDouble((String) map.get("sellPrice"));
      System.out.println(map);
      QueryWrapper<Good> wrapper = new QueryWrapper<>();
      wrapper.eq("good_id",id);
      Good good = goodMapper.selectOne(wrapper);
      good.setWholePrice(wholePrice);
      good.setSellPrice(sellPrice);
      goodMapper.updateById(good);
      return Result.success();
    }
    @PutMapping("/updatenumber")
    @CrossOrigin
    public Result<?> updateNumber(@RequestBody Map<String,Object> map)
    {
        Integer id = (Integer)map.get("id");
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.eq("good_id",id);
        Good good = goodMapper.selectOne(wrapper);
        goodMapper.updateById(good);
        return Result.success();
    }
    @DeleteMapping("/delete")
    @CrossOrigin
    public Result<?> delete(@RequestParam Integer id)
    {
      System.out.println(id);
      goodMapper.deleteById(id);
      return Result.success();
    }
    @PostMapping("/add")
    @CrossOrigin
    public Result<?> insert(@RequestBody Good good)
    {
      goodMapper.insert(good);
      return Result.success();
    }
}
