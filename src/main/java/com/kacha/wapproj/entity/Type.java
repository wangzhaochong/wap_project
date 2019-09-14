package com.kacha.wapproj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by Hayden on 2019/9/8.
 */
@Data
@TableName("type")
public class Type {

    @TableId("type_id")
    private Long typeId;

    @TableField("commodity_id")
    private Long commodityId;

    @TableField("type_name")
    private String typeName;




}
