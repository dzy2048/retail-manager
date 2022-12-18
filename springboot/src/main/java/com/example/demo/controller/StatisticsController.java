package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.Good;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Repository;
import com.example.demo.service.GoodService;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.RepositoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController
{
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private GoodService goodService;
    @Resource
    private OrderItemService orderItemService;

    @GetMapping("/repo")
    @CrossOrigin
    public Result<?> getRepoStatistics(@RequestParam("repoId") Integer repoId)
    {
        int storage = 0;    //库存积压量
        double overstock = 0;   //货品积压资金总额
        List<Map<String,Object>> details = new ArrayList<>();   //各货物积压详情
        Map<String,Object> result = new HashMap<>();
        QueryWrapper<Repository> wrapper = new QueryWrapper<>();
        wrapper.eq("repo_id",repoId);
        List<Repository> records = repositoryService.list(wrapper);
        for (Repository record : records)
        {
            storage += record.getNumber();
            Good good = goodService.getById(record.getGoodId());
            overstock += good.getBuyPrice() * record.getNumber();
            Map<String,Object> detail = new HashMap<>();
            detail.put("goodName",good.getGoodName());
            detail.put("money",good.getBuyPrice() * record.getNumber());
            details.add(detail);
        }
        result.put("storage",storage);
        result.put("overstock",overstock);
        result.put("details",details);
        return Result.success(result);
    }

    @GetMapping("/sale")
    @CrossOrigin
    public Result<?> getSaleStatistics()
    {
        List<Map<String,Object>> result = new ArrayList<>();
        QueryWrapper<OrderItem> wrapper = new QueryWrapper<>();
        wrapper.groupBy("good_id").select("good_id","sum(good_number) as total");
        List<Map<String,Object>> items = orderItemService.listMaps(wrapper);

        //获取总的货品销量
        int sum = 0;
        for (Map<String,Object> item : items)
        {
            BigDecimal total = (BigDecimal) item.get("total");
            sum += Integer.parseInt(total.toString());
        }

        //计算商品销量的百分比，同时根据good_id得到good_name
        for (Map<String,Object> item : items)
        {
            BigDecimal total = (BigDecimal) item.get("total");
            double percent = Double.parseDouble(total.toString()) / sum * 100;
            percent = Double.parseDouble(String.format("%.2f",percent));
            item.put("percent",percent);

            Good good = goodService.getById((Serializable) item.get("good_id"));
            item.put("goodName",good.getGoodName());
        }
        return Result.success(items);
    }
}
