package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.*;
import com.example.demo.mapper.GoodMapper;
import com.example.demo.mapper.RepositoryMapper;
import com.example.demo.service.RepositoryService;
import com.example.demo.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repo")
public class RepositoryController {
    @Resource
    GoodMapper goodMapper;
    @Resource
    RepositoryMapper repositoryMapper;
    @Resource
    private RepositoryService service;


    @PostMapping("/add")
    @CrossOrigin
    public Result<?> insert(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        List<?> goodList = (List<?>) map.get("addTable");
        Integer repoNum =Integer.parseInt((String)map.get("repoId"));
        int length = ((List<?>)map.get("addTable")).size();
        for (Object good : goodList)
        {
            Map<String,Object> goodMap = (Map<String, Object>) good;
            int goodId = (Integer) goodMap.get("goodId");
            int selectNum = (Integer) goodMap.get("selectNum");
            List<Repository> repositories = repositoryMapper.selectList(null);
            int flag = 0;
            if(selectNum!=0) {
                for (Repository repository : repositories) {
                    if (repository.getGoodId() == goodId && repository.getRepoId() == repoNum) {
                        repository.setNumber(selectNum + repository.getNumber());
                        repositoryMapper.updateById(repository);
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    Repository repository = new Repository();
                    repository.setGoodId(goodId);
                    repository.setRepoId(repoNum);
                    repository.setNumber(selectNum);
                    System.out.println(repository);
                    repositoryMapper.insert(repository);
                }
            }
        }


        return Result.success();
    }

    @GetMapping("/all")
    @CrossOrigin
    public Result<?> all()
    {
        List<Repository> repositories = repositoryMapper.selectList(null);
        List<RepoExt> repoExtList = new ArrayList<>();
        for (Repository repository : repositories)
        {
            Good good = goodMapper.selectById(repository.getGoodId());
            RepoExt repoExt = new RepoExt();
            repoExt.setUnit(good.getUnit());
            repoExt.setGoodName(good.getGoodName());
            repoExt.setRepoId(repository.getRepoId());
            repoExt.setNumber(repository.getNumber());
            repoExtList.add(repoExt);
        }
        return Result.success(repoExtList);
    }

    @GetMapping("/query")
    @CrossOrigin
    public Result<?> all(@RequestParam String goodName)
    {
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.eq("good_name",goodName);
        Good good = goodMapper.selectOne(wrapper);
        QueryWrapper<Repository> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("good_id",good.getGoodId());
        List<Repository> repositories = repositoryMapper.selectList(wrapper1);
        System.out.println(repositories);
        ArrayList<RepoExt> repoExts = new ArrayList<>();
        for (Repository repository : repositories)
        {
            RepoExt repoExt = new RepoExt();
            repoExt.setNumber(repository.getNumber());
            repoExt.setRepoId(repository.getRepoId());
            repoExt.setUnit(good.getUnit());
            repoExt.setGoodName(good.getGoodName());
            repoExts.add(repoExt);
        }

        return Result.success(repoExts);
    }
}
