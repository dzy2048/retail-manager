package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Repository;
import com.example.demo.mapper.RepositoryMapper;
import com.example.demo.service.RepositoryService;
import org.springframework.stereotype.Service;

@Service
public class RepositoryServiceImpl extends ServiceImpl<RepositoryMapper, Repository> implements RepositoryService {
}
