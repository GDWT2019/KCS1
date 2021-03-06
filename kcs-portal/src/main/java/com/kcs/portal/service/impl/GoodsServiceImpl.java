package com.kcs.portal.service.impl;

import com.kcs.portal.service.GoodsService;
import com.kcs.rest.pojo.AddOutBill;
import com.kcs.rest.pojo.Category;
import com.kcs.rest.pojo.Goods;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import com.kcs.rest.utils.Rest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Override
    public List<Goods> findGoodsByItemsName(String itemName) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"goods/getGoodsByItemName"+itemName);
            KcsResult result = KcsResult.formatToList(s, Goods.class);
            if (result.getStatus() == 200) {
                List<Goods> goodsList = (List<Goods>) result.getData();
                return goodsList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goods> findAllGoods() {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"goods/getALLGoods");
            KcsResult result = KcsResult.formatToList(s, Goods.class);
            if (result.getStatus() == 200) {
                List<Goods> goodsList = (List<Goods>) result.getData();
                return goodsList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goods> findAllGoodsName() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/goods/getALLGoodsName");
            KcsResult result = KcsResult.formatToList(s, Goods.class);
            if (result.getStatus() == 200) {
                List<Goods> goodsList = (List<Goods>) result.getData();
                return goodsList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goods> findGoodsByGoodsID(String goodsID) {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/goods/getByGoodsID" + goodsID);
            KcsResult result = KcsResult.formatToList(s, Goods.class);
            if (result.getStatus() == 200) {
                List<Goods> goodsList = (List<Goods>) result.getData();
                return goodsList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Goods findGoodsByItemsNameAndItemsType(Goods g) {
        try {
            String s = HttpClientUtil.doPostJson("http://localhost:8081/kcs_rest_war/goods/getGoodsByItemsNameAndItemsType", JsonUtils.objectToJson(g));
            KcsResult result = KcsResult.formatToPojo(s, Goods.class);
            if (result.getStatus() == 200) {
                Goods goods = (Goods) result.getData();
                return goods;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Goods findGoodsByGoodsID(int goodsID) {
        try {
            String s = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/goods/getGoodsById"+goodsID);
            KcsResult result = KcsResult.formatToPojo(s, Goods.class);
            if (result.getStatus() == 200) {
                Goods goods = (Goods) result.getData();
                return goods;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goods> findItemsNameUniqueByTime(String Time) {
        try {
            String s = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/goods/getItemsNameUniqueByTime"+Time);
            KcsResult result = KcsResult.formatToList(s, Goods.class);
            if (result.getStatus() == 200) {
                List<Goods> goodsList = (List<Goods>) result.getData();
                return goodsList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goods> findItemsNameUnique() {
        try {
            String s = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/goods/getItemsNameUnique");
            KcsResult result = KcsResult.formatToList(s, Goods.class);
            if (result.getStatus() == 200) {
                List<Goods> goodsList = (List<Goods>) result.getData();
                return goodsList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> findTheLastItemsNameByCategoryName(String categoryName) {
        try {
            String s = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/goods/findTheLastItemsNameByCategoryName"+categoryName);
            KcsResult result = KcsResult.formatToList(s, String.class);
            if (result.getStatus() == 200) {
                return (List<String>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AddOutBill> findAddOutBillByItemsName(String itemsName) {
        try {
            String s = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/goods/findAddOutBillByItemsName"+itemsName);
            KcsResult result = KcsResult.formatToList(s, AddOutBill.class);
            if (result.getStatus() == 200) {
                return (List<AddOutBill>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category findCategoryNameByID(Integer categoryID) {
        try {
            String s = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/goods/findCategoryNameByID"+categoryID);
            KcsResult result = KcsResult.formatToPojo(s, Category.class);
            if (result.getStatus() == 200) {
                Category category = (Category) result.getData();
                return category;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer addGoods(String goodsName, Integer categoryID, String goodsType, String goodsUnit,String image) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsName",goodsName);
        param.put("categoryID",categoryID + "");
        param.put("goodsType",goodsType);
        param.put("goodsUnit",goodsUnit);
        param.put("image",image);
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/goods/addGoods",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goods> goodsData(int before, int after) {
        HashMap<String, String> param = new HashMap<>();
        param.put("front",before+"");
        param.put("back",after+"");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"goods/goodsData",param);
            KcsResult result = KcsResult.formatToList(s,Goods.class);
            if (result.getStatus() == 200) {
                return (List<Goods>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Goods showUpdateGoodsByID(int goodsID) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"goods/showUpdateGoodsByID"+goodsID);
            KcsResult result = KcsResult.formatToPojo(s,Goods.class);
            if (result.getStatus() == 200) {
                Goods goods = (Goods) result.getData();
                return goods;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateGoods(Goods goods) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPostJson(Rest.rest+"goods/updateGoods", JsonUtils.objectToJson(goods));
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                i = (Integer) result.getData();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int delGoods(Goods goods) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPostJson(Rest.rest+"goods/delGoods", JsonUtils.objectToJson(goods));
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                i = (Integer) result.getData();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int countGoodsData() {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"goods/countGoodsData");
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (int) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Goods findOtherGoods(Integer goodsID, String itemsName,String itemsType ) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+"");
        param.put("itemsName",itemsName);
        param.put("itemsType",itemsType);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"goods/findOtherGoods",param);
            KcsResult result = KcsResult.formatToPojo(s,Goods.class);
            if (result.getStatus() == 200) {
                return (Goods) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
