package com.kacha.wapproj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kacha.wapproj.entity.Order;
import com.kacha.wapproj.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Hayden on 2019/9/8.
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {


}