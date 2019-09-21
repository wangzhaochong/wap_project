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
@TableName("order_info")
public class OrderInfo {

    @TableId("order_id")
    private Long orderId;

    @TableId("commodity_id")
    private Long commodityId;

    @TableId("user_id")
    private Long userId;

    @TableField("agent_id")
    private Long agentId;

    @TableField("share_id")
    private Long shareId;

    @TableField("price")
    private Double price;

    @TableField("paid")
    private Double paid;

    @TableField("create_time")
    private Date createTime;

    @TableField("status")
    private Integer status;


}
