package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)//主键自增
    private Integer userId;
    private String username;
    private String password;
    private Integer authority;

    public User(String username,String password,Integer authority)
    {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }
}
