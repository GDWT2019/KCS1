package com.kcs.portal.controller;

import com.kcs.portal.service.GoodsService;
import com.kcs.rest.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/goods")
@Controller
public class GoodController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/findGoodsByItemsName")
    @ResponseBody
    public List<Goods> findGoodsByItemName(String itemName){
        List<Goods> goodsList = goodsService.findGoodsByItemsName(itemName);
        return goodsList;
    }
}
