package com.kacha.wapproj.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kacha.wapproj.entity.Commodity;
import com.kacha.wapproj.entity.OrderInfo;
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
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    OrderService orderService;


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

    @PostMapping(value = "/user/sign")
    @ResponseBody
    public Object sign(HttpServletResponse response,
                       @RequestBody User user,
                       @RequestParam Long commodityId,
                       @RequestParam(required = false) Long agentId,
                       @RequestParam(required = false) Long shareId) {

        if(commodityId == null){
            return Resp.error();
        }

        User exist = userService.selectUserByTel(user.getPhoneNumber());
        if(exist == null){
            userService.addUser(user);
        }
        user = userService.selectUserByTel(user.getPhoneNumber());

        List<OrderInfo> orderInfos = orderService.selectOrderById(user.getUserId());
        if(orderInfos != null){
            for(OrderInfo orderInfo : orderInfos){
                if(orderInfo.getCommodityId().equals(commodityId)){
                    return Resp.error("您已团购过相同商品");
                }
            }
        }

        orderService.creatOrderInfo(user.getUserId(),commodityId,agentId,shareId);

        Cookie cookie = new Cookie("uid", String.valueOf(user.getUserId()));
        cookie.setMaxAge(-1);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);

        return Resp.ok();
    }

    @PostMapping(value = "/user/signNow")
    @ResponseBody
    public Object signNow(HttpServletResponse response,
                          @RequestParam Long commodityId,
                          @RequestParam Long uid,
                          @RequestParam(required = false) Long agentId,
                          @RequestParam(required = false) Long shareId) {

        if(commodityId == null){
            return Resp.error();
        }


        List<OrderInfo> orderInfos = orderService.selectOrderById(uid);
        if(orderInfos != null){
            for(OrderInfo orderInfo : orderInfos){
                if(orderInfo.getCommodityId().equals(commodityId)){
                    return Resp.error("您已团购过相同商品");
                }
            }
        }

        orderService.creatOrderInfo(uid,commodityId,agentId,shareId);

        Cookie cookie = new Cookie("uid", String.valueOf(uid));
        cookie.setMaxAge(-1);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);

        return Resp.ok();
    }



}
