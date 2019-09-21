package com.kacha.wapproj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kacha.wapproj.entity.OrderInfo;
import com.kacha.wapproj.entity.User;
import com.kacha.wapproj.mapper.OrderMapper;
import com.kacha.wapproj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hayden on 2019/9/11.
 */
@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;


    public List<OrderInfo> selectOrderById(Long uid){
        if(uid == null){
            return null;
        }

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", uid);
        queryWrapper.gt("status", 0);
        return orderMapper.selectList(queryWrapper);
    }


}
