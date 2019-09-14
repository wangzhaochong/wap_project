package com.kacha.wapproj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by Hayden on 2019/9/8.
 */
@Data
@TableName("user")
public class User {

    @TableId("user_id")
    private Long userId;

    @TableField("family_name")
    private String familyName;

    @TableField("given_name")
    private String givenName;

    @TableField("gender")
    private Integer gender;

    @TableField("phone_number")
    private String phoneNumber;

    @TableField("comment")
    private String comment;


}
