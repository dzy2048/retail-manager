package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodAndNumber {

        @TableId(type = IdType.AUTO)//主键自增
        private Integer goodId;
        private Double buyPrice;
        private Double wholePrice;
        private Double sellPrice;
        private String goodName;
        private String unit;
        private Integer selectNum;

}
