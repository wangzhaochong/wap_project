package com.kacha.wapproj.service;

import com.kacha.wapproj.entity.Shop;
import com.kacha.wapproj.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hayden on 2019/9/11.
 */
@Service
public class ShopService {

    @Autowired
    ShopMapper shopMapper;


    public List<Shop> selectShopList(){
        return shopMapper.selectList(null);
    }


}
