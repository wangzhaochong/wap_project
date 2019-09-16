package com.kacha.wapproj.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kacha.wapproj.entity.Commodity;
import com.kacha.wapproj.entity.Pic;
import com.kacha.wapproj.entity.User;
import com.kacha.wapproj.service.*;
import com.kacha.wapproj.util.CookieUtil;
import com.kacha.wapproj.util.Resp;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Hayden on 2019/9/7.
 */
@Controller
public class UserController {

    Logger log  = LoggerFactory.getLogger(this.getClass());


    @Autowired
    UserService userService;

    @Autowired
    CommodityService commodityService;

    @Autowired
    ShopService shopService;

    @Autowired
    TypeService typeService;

    @Autowired
    PicService picService;




    @PostMapping(value = "/user/login")
    @ResponseBody
    public Object login(HttpServletResponse response, @RequestBody User user) {

        userService.addUser(user);

        user = userService.selectUserByTel(user.getPhoneNumber());
        Cookie cookie = new Cookie("uid", String.valueOf(user.getUserId()));
        cookie.setMaxAge(-1);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);

        return Resp.ok();
    }



}
