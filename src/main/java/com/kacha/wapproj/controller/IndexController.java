package com.kacha.wapproj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kacha.wapproj.entity.Commodity;
import com.kacha.wapproj.entity.OrderInfo;
import com.kacha.wapproj.entity.Pic;
import com.kacha.wapproj.entity.User;
import com.kacha.wapproj.mapper.UserMapper;
import com.kacha.wapproj.service.*;
import com.kacha.wapproj.util.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Hayden on 2019/9/7.
 */
@Controller
public class IndexController {

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


    @GetMapping(value = "/index")
    public String index(HttpServletRequest req, Model model) {

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
                model.addAttribute("orderCount", orderList.size());
            }else{
                model.addAttribute("orderCount", 0);
            }
        }else{
            model.addAttribute("orderCount", 0);
        }


        List<Commodity> commodityList = commodityService.selectCommodityExtendList();
        List<Map<String,Object>> typeList = Lists.newArrayList();
        if(commodityList != null){
            Map<String,List<Commodity>> typeMap = Maps.newHashMap();
            List<Long> idList = Lists.newArrayList();

            for(Commodity commodity : commodityList){
                //修改日期格式
                commodity.setDeadline(sdf.format(commodity.getEndTime()));

                //分类
                List<Commodity> comList = typeMap.get(commodity.getTypeName());
                if(comList == null){
                    comList = Lists.newArrayList();
                }
                comList.add(commodity);
                typeMap.put(commodity.getTypeName(), comList);

                idList.add(commodity.getCommodityId());
            }

            //取图片
            List<Pic> picList = picService.selectPicListByCommodityId(idList, true);
            Map<Long, String> srcMap = Maps.newHashMap();
            for(Pic pic : picList){
                srcMap.put(pic.getCommodityId(),pic.getSrc());
            }

            //排序
            for(String type : typeMap.keySet()){
                List<Commodity> comList = typeMap.get(type);
                //加入图片、订单状态
                for(Commodity commodity : comList){

                    List<String> srcList = Lists.newArrayList();
                    srcList.add(srcMap.get(commodity.getCommodityId()));
                    commodity.setSrcList(srcList);

                    if(orderStatusMap.get(commodity.getCommodityId()) != null){
                        commodity.setOrderStatus(orderStatusMap.get(commodity.getCommodityId()));
                    }else{
                        commodity.setOrderStatus(-1);
                    }
                }

                //分类内排序
                Collections.sort(comList, new Comparator<Commodity>() {
                    @Override
                    public int compare(Commodity o1, Commodity o2) {
                        return o1.getCommodityIndex() - o2.getCommodityIndex();
                    }
                });


                //封装分类
                Map<String,Object> typeItem = Maps.newHashMap();
                typeItem.put("typeName",type);
                typeItem.put("itemList",comList);
                typeList.add(typeItem);
            }
            //分类排序
            Collections.sort(typeList, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    if(o1.get("itemList") != null
                            && o2.get("itemList") != null){
                        List<Commodity> o1List = (List<Commodity>) o1.get("itemList");
                        List<Commodity> o2List = (List<Commodity>) o2.get("itemList");
                        if(o1List.get(0) != null
                                && o2List.get(0) != null){
                            return o1List.get(0).getTypeIndex() - o2List.get(0).getTypeIndex();
                        }
                    }

                    return 0;
                }
            });

        }
        model.addAttribute("typeList", typeList);
        return "/wap/index";
    }


    @GetMapping(value = "/getUserById")
    @ResponseBody
    public Object getUserById(HttpServletRequest req, Long uid) {

        return userService.selectUserById(uid);
    }

    @GetMapping(value = "/getList")
    @ResponseBody
    public Object getList(HttpServletRequest req) {

        List<Long> idList = Arrays.asList(new Long[]{1l,2l,4l});
        return picService.selectPicListByCommodityId(idList, true);
    }



}
