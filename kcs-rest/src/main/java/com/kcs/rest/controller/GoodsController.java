package com.kcs.rest.controller;

import com.kcs.rest.pojo.Goods;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/goods")
@Controller("goodsController")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/getGoodsByItemName{itemName}")
    @ResponseBody
    public KcsResult getGoodsByItemName(@PathVariable String itemName){
        List<Goods> goodsList = goodsService.findGoodsByItemName(itemName);
        if (goodsList != null) {
            return KcsResult.ok(goodsList);
        } else
            return KcsResult.build(500, "未找到对应物品");
    }

    //获取用户数据
    @RequestMapping(value="getALLGoods")
    @ResponseBody
    public  KcsResult getALLGoods(){
        List<Goods> allGoods = goodsService.findAllGoods();
        return KcsResult.ok(allGoods);
    }

    @RequestMapping("/getByGoodsID{goodsID}")
    @ResponseBody
    public KcsResult getByGoodsID(@PathVariable String goodsID){
        int id = Integer.parseInt( goodsID );
        List<Goods> goodsById = goodsService.findGoodsBygoodsId(id);
        return  KcsResult.ok(goodsById);
    }
}
