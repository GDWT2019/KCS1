package com.kcs.rest.dao;

import com.kcs.rest.pojo.Goods;

import java.util.List;

public interface GoodsDao {

    //根据id查找物品
    Goods findGoodsById(int id);

    //联合汇总表查找物品
    List<Goods> findGoodsInSummaryGoodsID();

    //根据物品名称查找物品
    List<Goods> findGoodsByItemName(String itemName);
}
