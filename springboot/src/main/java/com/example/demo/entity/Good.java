package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("good")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good {
    @TableId(type = IdType.AUTO)//主键自增
    private Integer goodid;
    private Double bringprice;
    private Double pifaprice;
    private Double lingshouprice;
    private String goodname;
    private String unit;
}
