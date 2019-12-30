package com.kcs.rest.controller;

import com.kcs.rest.pojo.AddOutBill;
import com.kcs.rest.pojo.Category;
import com.kcs.rest.pojo.Goods;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
            return KcsResult.build(500, "根据物品名，未找到对应物品");
    }

    @RequestMapping("/getGoodsByItemsNameAndItemsType")
    @ResponseBody
    public KcsResult getGoodsByItemsNameAndItemsType(@RequestBody Goods g){
        Goods goods = goodsService.findGoodsByItemsNameAndItemsType(g.getItemsName(),g.getItemsType());
        if (goods != null) {
            return KcsResult.ok(goods);
        } else
            return KcsResult.build(500, "根据物品名和规格，未找到对应物品");
    }

    @RequestMapping("/getGoodsById{goodsID}")
    @ResponseBody
    public KcsResult getGoodsById(@PathVariable int goodsID){
        Goods goods = goodsService.findGoodsById(goodsID);
        if (goods != null) {
            return KcsResult.ok(goods);
        } else
            return KcsResult.build(500, "根据物品ID，未找到对应物品");
    }

    @RequestMapping("/findCategoryNameByID{categoryID}")
    @ResponseBody
    public KcsResult findCategoryNameByID(@PathVariable int categoryID){
        Category category = goodsService.findCategoryNameByID(categoryID);
        if (category != null) {
            return KcsResult.ok(category);
        } else
            return KcsResult.build(500, "根据物品ID，未找到对应物品");
    }

    @RequestMapping("/getItemsNameUnique")
    @ResponseBody
    public KcsResult getItemsNameUnique(){
        List<Goods> goodsList = goodsService.findItemsNameUnique();
        if (goodsList != null) {
            return KcsResult.ok(goodsList);
        } else
            return KcsResult.build(500, "根据物品，未找到对应物品");
    }

    //获取物品数据
    @RequestMapping(value="getALLGoods")
    @ResponseBody
    public  KcsResult getALLGoods(){
        List<Goods> allGoods = goodsService.findAllGoods();
        return KcsResult.ok(allGoods);
    }
    //获取用户数据
    @RequestMapping(value="getALLGoodsName")
    @ResponseBody
    public  KcsResult getALLGoodsName(){
        List<Goods> allGoods = goodsService.findAllGoodsName();
        return KcsResult.ok(allGoods);
    }

    @RequestMapping("/getByGoodsID{goodsID}")
    @ResponseBody
    public KcsResult getByGoodsID(@PathVariable String goodsID){
        int id = Integer.parseInt( goodsID );
        List<Goods> goodsById = goodsService.findGoodsBygoodsId(id);
        return  KcsResult.ok(goodsById);
    }


    //根据物品类型，查询物品的详情及该物品对应汇总表最新数据
    @RequestMapping("/findAddOutBillByCategoryName{categoryName}")
    @ResponseBody
    public KcsResult findAddOutBillByCategoryName(@PathVariable String categoryName){
        List<AddOutBill> addOutBills = goodsService.findAddOutBillByCategoryName(categoryName);
        return KcsResult.ok(addOutBills);
    }

    //物品类型》》物品id》》过滤汇总表最新数据（确定已入库且最新时间的物品》》过滤后的物品id》》物品名称》按名称分组
    @RequestMapping("/findTheLastItemsNameByCategoryName{categoryName}")
    @ResponseBody
    public KcsResult findTheLastItemsNameByCategoryName(@PathVariable String categoryName){
        return KcsResult.ok(goodsService.findTheLastItemsNameByCategoryName(categoryName));
    }

    //根据物品名称，查询物品数据及汇总表的该物品的最新数据;
    @RequestMapping("/findAddOutBillByItemsName{itemsName}")
    @ResponseBody
    public KcsResult findAddOutBillByItemsName(@PathVariable String itemsName){
        return KcsResult.ok(goodsService.findAddOutBillByItemsName(itemsName));
    }
}
