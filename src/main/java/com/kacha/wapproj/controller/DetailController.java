package com.kacha.wapproj.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kacha.wapproj.entity.Commodity;
import com.kacha.wapproj.entity.OrderInfo;
import com.kacha.wapproj.entity.Pic;
import com.kacha.wapproj.entity.User;
import com.kacha.wapproj.service.*;
import com.kacha.wapproj.util.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Hayden on 2019/9/7.
 */
@Controller
public class DetailController {

    Logger log  = LoggerFactory.getLogger(this.getClass());

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");


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


    @GetMapping(value = "/detail")
    public String detail(@RequestParam Long commodityId,
                         HttpServletRequest req,
                         Model model) {

        //添加登录user
        String ckUid = CookieUtil.resolveINfoFromCookie(req.getCookies(), "uid");
        User user = null;
        if(StringUtils.isNotBlank(ckUid)){
            Long uid = Long.valueOf(ckUid);
            user = userService.selectUserById(uid);
            if(user.getGender() == 1){
                model.addAttribute("gender", "先生");
            }else{
                model.addAttribute("gender", "女士");
            }
            model.addAttribute("user", user);
        }else{
            model.addAttribute("user", null);
        }

        Map<Long,Integer> orderStatusMap = Maps.newHashMap();
        if(user != null){
            List<OrderInfo> orderList = orderService.selectOrderById(user.getUserId());
            if(orderList != null
                    && orderList.size() > 0){
                for(OrderInfo order : orderList){
                    orderStatusMap.put(order.getCommodityId(),order.getStatus());
                }
            }
        }

        Commodity query = new Commodity();
        query.setStatus(1);
        query.setCommodityId(commodityId);
        Commodity commodity = commodityService.selectCommodityExtend(query);
        if(commodity != null){
            //修改日期格式
            commodity.setDeadline(sdf.format(commodity.getEndTime()));

            //取图片
            List<Long> idList = Lists.newArrayList();
            idList.add(commodity.getCommodityId());
            List<Pic> picList = picService.selectPicListByCommodityId(idList, false);
            List<String> scrList = Lists.newArrayList();
            //分类内排序
            if(picList != null){
                Collections.sort(picList, new Comparator<Pic>() {
                    @Override
                    public int compare(Pic o1, Pic o2) {
                        return o1.getPicIndex() - o2.getPicIndex();
                    }
                });

                for(Pic pic : picList){
                    scrList.add(pic.getSrc());
                }
            }

            commodity.setSrcList(scrList);
        }

        if(orderStatusMap.get(commodity.getCommodityId()) != null){
            commodity.setOrderStatus(orderStatusMap.get(commodity.getCommodityId()));
        }else{
            commodity.setOrderStatus(-1);
        }


        model.addAttribute("commodity", commodity);
        return "/wap/detail";
    }




}
