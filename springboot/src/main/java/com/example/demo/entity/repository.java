package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("repository")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class repository {
    private Integer goodid;
    private String goodname;
    private Integer quantity;
    private Integer reponum;

}
