package com.kacha.wapproj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kacha.wapproj.entity.Commodity;
import com.kacha.wapproj.entity.User;
import com.kacha.wapproj.mapper.CommodityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hayden on 2019/9/11.
 */
@Service
public class CommodityService {

    @Autowired
    CommodityMapper commodityMapper;


    public List<Commodity> selectCommodityList(){

        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        return commodityMapper.selectList(queryWrapper);
    }

    public List<Commodity> selectCommodityExtendList(){

        return commodityMapper.selectCommodityList();
    }


}
