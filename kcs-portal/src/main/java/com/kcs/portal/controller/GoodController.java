package com.kcs.portal.controller;

import com.kcs.portal.service.GoodsService;
import com.kcs.rest.pojo.Category;
import com.kcs.rest.pojo.Goods;
import com.kcs.rest.utils.AjaxMesg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/goods")
@Controller
public class GoodController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/addGoods")
    @ResponseBody
    public AjaxMesg addGoods(String goodsName, Integer categoryID, String goodsType,String goodsUnit){

        Goods goods =new Goods();
        goods.setItemsName(goodsName);
        goods.setItemsType(goodsType);
        Goods g = goodsService.findGoodsByItemsNameAndItemsType(goods);
        if(g==null){
            Integer integer = goodsService.addGoods(goodsName,categoryID,goodsType,goodsUnit);
            if (integer<0)
                return new AjaxMesg(false,"新增物品失败！");
            return new AjaxMesg(true,"新增物品成功!");
        }else{
            return new AjaxMesg(true,"新增物品失败，物品已存在!");
        }

    }

    @RequestMapping("/rAddGoods")
    public String rAddGoods(){
        return "addGoods";
    }

    @RequestMapping("/findGoodsByItemsName")
    @ResponseBody
    public List<Goods> findGoodsByItemName(HttpServletRequest request){
        String itemsName = request.getParameter("itemsName");
        List<Goods> goodsList = goodsService.findGoodsByItemsName(itemsName);
        List<Category> categories = new ArrayList<>();

        for (Goods goods : goodsList) {
            Integer categoryID = goods.getCategoryID();
            Category category=goodsService.findCategoryNameByID(categoryID);
            categories.add(category);
            goods.setCategories(categories);
        }
        return goodsList;
    }

    @RequestMapping("/findGoodsByItemsNameAndItemsType")
    @ResponseBody
    public Goods findSummaryByItemsNameAndItemsType(HttpServletRequest request) {
        String itemsType = request.getParameter("itemsType");
        String itemsName = request.getParameter("itemsName");
        Goods g =new Goods();
        g.setItemsName(itemsName);
        g.setItemsType(itemsType);

        Goods goods = goodsService.findGoodsByItemsNameAndItemsType(g);     //根据品名和规格查找物品
        return goods;
    }



    @RequestMapping(value="getGoodsName",produces="text/html;charset=utf-8")
    public @ResponseBody String findAllGoodsName(){
        List<Goods> list=goodsService.findAllGoodsName();
        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        System.out.println(js);
        return js;
    }
}
