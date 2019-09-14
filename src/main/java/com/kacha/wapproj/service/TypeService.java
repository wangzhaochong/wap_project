package com.kacha.wapproj.service;

import com.kacha.wapproj.entity.Type;
import com.kacha.wapproj.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hayden on 2019/9/11.
 */
@Service
public class TypeService {

    @Autowired
    TypeMapper typeMapper;


    public List<Type> selectTypeList(){

        return typeMapper.selectList(null);
    }


}
