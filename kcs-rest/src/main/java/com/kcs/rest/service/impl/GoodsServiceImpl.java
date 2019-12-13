package com.kcs.rest.service.impl;

import com.kcs.rest.dao.GoodsDao;
import com.kcs.rest.pojo.Category;
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
    public List<Goods> findAllGoodsName() {
        return goodsDao.findAllGoodsName();
    }

    @Override
    public List<Goods> findGoodsBygoodsId(int id) {
        return goodsDao.findGoodsBygoodsId(id);
    }

    @Override
    public Goods findGoodsByItemsNameAndItemsType(String itemsName, String itemsType) {
        Goods g= goodsDao.findGoodsByItemsNameAndItemsType(itemsName,itemsType);
        return g;
    }

    @Override
    public List<Goods> findItemsNameUniqueByTime(String Time) {
        return goodsDao.findItemsNameUniqueByTime(Time);
    }

    @Override
    public List<Goods> findItemsNameUnique() {
        return goodsDao.findItemsNameUnique();
    }

    @Override
    public Category findCategoryNameByID(int categoryID) {
        return goodsDao.findCategoryNameByID(categoryID);
    }
}
