package com.kcs.rest.service;

import com.kcs.rest.pojo.Goods;

import java.util.List;

public interface GoodsService {

    //根据id查找物品
    Goods findGoodsById(int id);

    //联合汇总表查找物品
    List<Goods> findGoodsInSummaryGoodsID();

    //根据物品名称查找物品
    List<Goods> findGoodsByItemName(String itemName);

    List<Goods> findAllGoods();

    List<Goods> findGoodsBygoodsId(int id);
}
