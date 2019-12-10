package com.kcs.rest.service.impl;

import com.kcs.rest.dao.GoodsDao;
import com.kcs.rest.pojo.Goods;
import com.kcs.rest.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public Goods findGoodsById(int id) {
        return goodsDao.findGoodsById(id);
    }

    @Override
    public List<Goods> findGoodsInSummaryGoodsID() {
        return goodsDao.findGoodsInSummaryGoodsID();
    }

    @Override
    public List<Goods> findGoodsByItemName(String itemName) {
        return goodsDao.findGoodsByItemName(itemName);
    }

    @Override
    public List<Goods> findAllGoods() {
        return goodsDao.findAllGoods();
    }

    @Override
    public List<Goods> findGoodsBygoodsId(int id) {
        return goodsDao.findGoodsBygoodsId(id);
    }
}
