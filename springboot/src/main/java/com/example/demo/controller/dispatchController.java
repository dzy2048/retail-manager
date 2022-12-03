package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.commom.Result;
import com.example.demo.entity.Good;
import com.example.demo.entity.RepoExt;
import com.example.demo.entity.Repository;
import com.example.demo.mapper.GoodMapper;
import com.example.demo.mapper.RepositoryMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index/dispatch")
public class dispatchController {
    @Resource
    GoodMapper goodMapper;
    @Resource
    RepositoryMapper repositoryMapper;

    @GetMapping("/all")
    @CrossOrigin
    public Result<?> getAll(@RequestParam Integer id) {
        QueryWrapper<Repository> wrapper = new QueryWrapper<>();
        wrapper.eq("repo_id", id);
        List<Repository> repositories = repositoryMapper.selectList(wrapper);
        List<RepoExt> repoExtList = new ArrayList<>();
        for (Repository repository : repositories)
        {
            QueryWrapper<Good> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("good_id", repository.getGoodId());
            Good good = goodMapper.selectOne(wrapper1);
            RepoExt repoExt = new RepoExt();
            repoExt.setNumber(repository.getNumber());
            repoExt.setGoodName(good.getGoodName());
            repoExtList.add(repoExt);
        }
        return Result.success(repoExtList);
    }

    @PostMapping("/move")
    @CrossOrigin
    public Result<?> move(@RequestBody Map<String, Object> map) {
        List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("moveList");
        System.out.println(map);
        for (Map<String,Object> goodMap : list)
        {
            String goodName = (String) goodMap.get("goodName");
            int dispatchNum = (int) goodMap.get("dispatchNum");
            int source_id = Integer.parseInt((String) map.get("source_id"));
            int target_id = Integer.parseInt((String) map.get("target_id"));

            //获取商品ID
            QueryWrapper<Good> wrapper_good = new QueryWrapper<>();
            wrapper_good.eq("good_name",goodName);
            Good good = goodMapper.selectOne(wrapper_good);
            int goodId = good.getGoodId();

            //根据goodId和source_id查询出源仓库中该条记录
            QueryWrapper<Repository> wrapper_repo = new QueryWrapper<>();
            wrapper_repo.eq("good_id",goodId).eq("repo_id",source_id);
            Repository repo_item =  repositoryMapper.selectOne(wrapper_repo);

            //根据调度数量更新源仓库
            int number = repo_item.getNumber();
            int new_num = number - dispatchNum;
            System.out.println(number+" "+new_num);
            repo_item.setNumber(new_num);
            if (new_num == 0)   //全部调到另一个仓库
            {
                repositoryMapper.deleteById(repo_item.getId());
            }
            else    //有剩余
            {
                repositoryMapper.updateById(repo_item);
            }

            //根据目的仓库是否已有该商品更新目的仓库
            QueryWrapper<Repository> wrapper_repo1 = new QueryWrapper<>();
            wrapper_repo1.eq("good_id",goodId).eq("repo_id",target_id);
            Repository repo_target = repositoryMapper.selectOne(wrapper_repo1);
            if (repo_target != null)    //已有该商品
            {
                repo_target.setNumber(repo_target.getNumber()+dispatchNum);
                repositoryMapper.updateById(repo_target);
            }
            else
            {
                Repository new_repo = new Repository();
                new_repo.setNumber(dispatchNum);
                new_repo.setGoodId(goodId);
                new_repo.setRepoId(target_id);
                repositoryMapper.insert(new_repo);
            }
        }
        return Result.success();
    }

}
