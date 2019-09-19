package com.kacha.wapproj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Created by Hayden on 2019/9/8.
 */
@Data
@TableName("order")
public class Order {

    @TableId("order_id")
    private Long order_id;

    @TableId("commodity_id")
    private Long commodity_id;

    @TableId("user_id")
    private Long user_id;

    @TableField("agent_id")
    private Long agent_id;

    @TableField("share_id")
    private Long share_id;

    @TableField("price")
    private Double price;

    @TableField("paid")
    private Double paid;

    @TableField("create_time")
    private Date create_time;

    @TableField("status")
    private Integer status;


}
