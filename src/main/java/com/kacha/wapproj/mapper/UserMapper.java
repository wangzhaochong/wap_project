package com.kacha.wapproj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kacha.wapproj.entity.User;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Hayden on 2019/9/8.
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserList();

}