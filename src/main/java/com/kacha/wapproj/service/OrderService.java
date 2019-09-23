package com.kacha.wapproj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kacha.wapproj.entity.Commodity;
import com.kacha.wapproj.entity.OrderInfo;
import com.kacha.wapproj.entity.User;
import com.kacha.wapproj.mapper.OrderMapper;
import com.kacha.wapproj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Hayden on 2019/9/11.
 */
@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    CommodityService commodityService;

    public List<OrderInfo> selectOrderById(Long uid){
        if(uid == null){
            return null;
        }

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", uid);
        queryWrapper.gt("status", 0);
        return orderMapper.selectList(queryWrapper);
    }

    public int creatOrderInfo(Long uid, Long commodityId, Long agentId, Long shareId){
        if(uid == null && commodityId == null){
            return -1;
        }

        Commodity query = new Commodity();
        query.setStatus(1);
        query.setCommodityId(commodityId);
        Commodity commodity = commodityService.selectCommodityExtend(query);

        if(commodity == null){
            return -1;
        }

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(uid);
        orderInfo.setCommodityId(commodityId);
        orderInfo.setPrice(commodity.getPriceCur());
        orderInfo.setPaid(0d);
        orderInfo.setCreateTime(new Date());
        orderInfo.setStatus(1);
        if(agentId != null){
            orderInfo.setAgentId(agentId);
        }
        if(shareId != null){
            orderInfo.setShareId(shareId);
        }
        return orderMapper.insert(orderInfo);
    }



}
