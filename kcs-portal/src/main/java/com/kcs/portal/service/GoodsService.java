package com.kcs.portal.service;

import com.kcs.rest.pojo.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> findGoodsByItemName(String itemName);

    List<Goods> findAllGoods();

    List<Goods> findGoodsByGoodsID(String goodsID);
}
