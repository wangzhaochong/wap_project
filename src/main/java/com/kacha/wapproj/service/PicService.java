package com.kacha.wapproj.service;

import com.kacha.wapproj.entity.Pic;
import com.kacha.wapproj.mapper.PicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hayden on 2019/9/11.
 */
@Service
public class PicService {

    @Autowired
    PicMapper picMapper;


    public List<Pic> selectPicListByCommodityId(List<Long> commodityList){

        return picMapper.selectPicListByCommodityId(commodityList);
    }


}
