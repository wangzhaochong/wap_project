package com.kacha.wapproj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by Hayden on 2019/9/8.
 */
@Data
@TableName("pic")
public class Pic {

    @TableId("pic_id")
    private Long picId;

    @TableField("commodity_id")
    private Long commodityId;

    @TableField("src")
    private String src;

    @TableField("pic_index")
    private Integer picIndex;

    @TableField("status")
    private Integer status;



}
