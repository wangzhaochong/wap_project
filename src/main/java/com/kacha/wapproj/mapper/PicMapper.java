package com.kacha.wapproj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kacha.wapproj.entity.Pic;
import com.kacha.wapproj.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Hayden on 2019/9/8.
 */
@Mapper
public interface PicMapper extends BaseMapper<Pic> {

    List<Pic> selectPicListByCommodityId(@Param("list") List<Long> commodityIdList);

    List<Pic> selectAllPicListByCommodityId(@Param("list") List<Long> commodityIdList);

}