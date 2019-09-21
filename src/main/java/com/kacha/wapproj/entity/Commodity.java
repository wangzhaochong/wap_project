package com.kacha.wapproj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Hayden on 2019/9/8.
 */
@Data
@TableName("commodity")
public class Commodity {

    @TableId("commodity_id")
    private Long commodityId;

    @TableField("commodity_index")
    private Integer commodityIndex;

    @TableField("shop_id")
    private Long shopId;

    @TableField("type_id")
    private Long typeId;

    @TableField("commodity_name")
    private String commodityName;

    @TableField("price_cur")
    private Double priceCur;

    @TableField("price_line")
    private Double priceLine;

    @TableField("buyer_count")
    private Integer buyerCount;

    @TableField("least_buyer_count")
    private Integer leastBuyerCount;

    @TableField("buyer_count_real")
    private Integer buyerCountReal;

    @TableField("least_buyer_count_real")
    private Integer leastBuyerCountReal;

    @TableField("end_time")
    private Date endTime;

    @TableField("status")
    private Integer status;

    @TableField("comment")
    private String comment;

    @TableField(exist = false)
    private String shopName;
    @TableField(exist = false)
    private String location;
    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private Integer typeIndex;
    @TableField(exist = false)
    private List<String> srcList;
    @TableField(exist = false)
    private String deadline;
    @TableField(exist = false)
    private Integer orderStatus;




}
