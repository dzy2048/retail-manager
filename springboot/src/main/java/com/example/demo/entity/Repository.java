package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("repository")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repository {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer goodId;
    private Integer number;
    private Integer repoId;

}
