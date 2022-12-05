package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用于客户端记录用户登录状态
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientUser
{
    private String username;
    private int userId;
    private int authority;
}
