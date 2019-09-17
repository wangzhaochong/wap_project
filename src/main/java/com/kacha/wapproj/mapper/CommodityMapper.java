package com.kacha.wapproj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kacha.wapproj.entity.Commodity;
import com.kacha.wapproj.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Hayden on 2019/9/8.
 */
@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {

    List<Commodity> selectCommodityList();

    Commodity selectCommodityExtend(Commodity query);

}