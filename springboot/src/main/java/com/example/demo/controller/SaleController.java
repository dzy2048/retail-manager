package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.*;
import com.example.demo.service.GoodService;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.RepositoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Resource
    private GoodService goodService;
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private RepositoryService repositoryService;

    @GetMapping("/all")
    @CrossOrigin
    public Result<?> getAll()
    {
        List<SaleItem> items = new ArrayList<>();
        List<Good> goods = goodService.list();
        for (Good good : goods)
        {
            //选出包含该商品的仓库条目
            QueryWrapper<Repository> wrapper = new QueryWrapper<>();
            wrapper.eq("good_id",good.getGoodId());
            List<Repository> repos = repositoryService.list(wrapper);
            for (Repository repo : repos)
            {
                SaleItem item = new SaleItem();
                item.setGoodId(good.getGoodId());
                item.setGoodName(good.getGoodName());
                item.setPrice(good.getWholePrice());
                item.setUnit(good.getUnit());
                item.setNumber(repo.getNumber());
                item.setRepoId(repo.getRepoId());
                items.add(item);
            }
        }
        return Result.success(items);
    }

    @GetMapping("/query")
    @CrossOrigin
    public Result<?> findGood(@RequestParam String goodName)
    {
        List<SaleItem> items = new ArrayList<>();

        QueryWrapper<Good> wrapper_good = new QueryWrapper<>();
        wrapper_good.eq("good_name",goodName);
        Good good = goodService.getOne(wrapper_good);

        QueryWrapper<Repository> wrapper_repo = new QueryWrapper<>();
        wrapper_repo.eq("good_id",good.getGoodId());
        List<Repository> repos = repositoryService.list(wrapper_repo);
        for (Repository repo : repos)
        {
            SaleItem item = new SaleItem();
            item.setGoodId(good.getGoodId());
            item.setGoodName(good.getGoodName());
            item.setPrice(good.getWholePrice());
            item.setUnit(good.getUnit());
            item.setNumber(repo.getNumber());
            item.setRepoId(repo.getRepoId());
            items.add(item);
        }
        return Result.success(items);
    }
//    @PostMapping("/submit")
//    public Result<?> submit(@RequestBody OrderSubmit orderSubmit)
//    {
//        Order order = new Order();
//        order.setTime(orderSubmit.getOrderperiod());
//        order.setcustomerId(orderSubmit.getCustomerid());
//        order.setTotalprice(order.getTotalprice());
//        orderMapper.insert(order);
//        for(OrderItem orderItem : orderSubmit.getOrderitems())
//        {
//            orderItemMapper.insert(orderItem);
//        }
//        return Result.success();
//    }
}
