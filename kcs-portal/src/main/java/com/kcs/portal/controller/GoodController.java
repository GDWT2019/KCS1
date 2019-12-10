package com.kcs.portal.controller;

import com.kcs.portal.service.GoodsService;
import com.kcs.rest.pojo.Goods;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/goods")
@Controller
public class GoodController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/findGoodsByItemsName")
    @ResponseBody
    public List<Goods> findGoodsByItemName(String itemName, HttpServletRequest request){
        List<Goods> goodsList = goodsService.findGoodsByItemName(itemName);
        return goodsList;
    }

    @RequestMapping(value="getGoods",produces="text/html;charset=utf-8")
    public @ResponseBody String getPosition(){
        List<Goods> list=goodsService.findAllGoods();
        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        System.out.println(js);
        return js;
    }
}
