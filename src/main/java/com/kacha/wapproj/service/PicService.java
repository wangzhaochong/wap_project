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


    /**
     * 获取商品对应的图片
     * @param commodityList
     * @param prime 是否获取首图/所有图片
     * @return
     */
    public List<Pic> selectPicListByCommodityId(List<Long> commodityList, boolean prime){

        if(prime){
            return picMapper.selectPicListByCommodityId(commodityList);
        }else {
            return picMapper.selectAllPicListByCommodityId(commodityList);
        }
    }


}
