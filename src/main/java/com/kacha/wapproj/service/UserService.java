package com.kacha.wapproj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kacha.wapproj.entity.Commodity;
import com.kacha.wapproj.entity.User;
import com.kacha.wapproj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hayden on 2019/9/11.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    public User selectUserById(Long uid){
        if(uid == null){
            return null;
        }

        return userMapper.selectById(uid);
    }

    public void addUser(User user){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_number", user.getPhoneNumber());
        User exist = userMapper.selectOne(queryWrapper);
        if(exist == null) {
            userMapper.insert(user);
        }
    }

    public User selectUserByTel(String tel){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_number", tel);
        return userMapper.selectOne(queryWrapper);
    }


}
