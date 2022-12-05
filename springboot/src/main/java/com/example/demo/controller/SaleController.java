package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.*;
import com.example.demo.service.GoodService;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;
import com.example.demo.service.RepositoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
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
    @Resource
    private OrderService orderService;

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

    @PostMapping("/add")
    @CrossOrigin
    public Result<?> add(@RequestBody Map<String,Object> map)
    {
        System.out.println(map);
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> saleItems = (List<Map<String, Object>>) map.get("items");
        //新建订单
        Order order = new Order();
        order.setState("未完成");
        order.setUserId(Integer.parseInt((String) map.get("userId")));
        order.setCustomerId((Integer) map.get("customerId"));

        //计算利润
        double profit = 0;
        double shouldPay = 0;
        for (Map<String,Object> saleItem : saleItems)
        {
            Good good = goodService.getById((Serializable) saleItem.get("goodId"));
            double buyPrice = good.getBuyPrice();
            double wholePrice = good.getWholePrice();
            profit += (wholePrice - buyPrice) * (int) saleItem.get("number");
            shouldPay += wholePrice;
        }
        order.setShouldPay(shouldPay);
        order.setProfit(profit);
        order.setHavePaid(0.0);
        if (!orderService.save(order))
            return Result.error("1","订单保存失败");

        //新建订单项
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("order_id");
        wrapper.last("limit 1");
        int orderId = orderService.getOne(wrapper).getOrderId();
        for (Map<String,Object> saleItem : saleItems)
        {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setGoodId((Integer) saleItem.get("goodId"));
            orderItem.setGoodNumber((Integer) saleItem.get("number"));
            orderItem.setGoodPrice(Double.parseDouble(saleItem.get("price").toString()));
            if (!orderItemService.save(orderItem))
                return Result.error("1","订单项保存失败");

            //删除仓库中对应的数量
            QueryWrapper<Repository> wrapper_repo = new QueryWrapper<>();
            wrapper_repo.eq("good_id",saleItem.get("goodId")).eq("repo_id",saleItem.get("repoId"));
            Repository repo_item = repositoryService.getOne(wrapper_repo);
            int number = repo_item.getNumber();
            int new_num = number - (Integer) saleItem.get("number");
            if (new_num == 0)
                repositoryService.removeById(repo_item.getId());
            else
            {
                repo_item.setNumber(new_num);
                repositoryService.updateById(repo_item);
            }
        }
        return Result.success();
    }
}
