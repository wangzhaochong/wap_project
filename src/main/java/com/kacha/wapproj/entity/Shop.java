package com.kacha.wapproj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by Hayden on 2019/9/8.
 */
@Data
@TableName("shop")
public class Shop {

    @TableId("shop_id")
    private Long shopId;


    @TableField("shop_name")
    private String shopName;

    @TableField("location")
    private String location;



}
