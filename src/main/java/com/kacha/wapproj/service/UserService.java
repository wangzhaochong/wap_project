package com.kacha.wapproj.service;

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


}
