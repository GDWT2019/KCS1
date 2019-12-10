package com.kcs.portal.service.impl;

import com.kcs.portal.service.GoodsService;
import com.kcs.rest.pojo.Goods;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Override
    public List<Goods> findGoodsByItemName(String itemName) {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/goods/getGoodsByItemName"+itemName);
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
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/goods/getALLGoods");
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
}
