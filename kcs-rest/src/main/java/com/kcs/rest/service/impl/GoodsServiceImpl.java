package com.kcs.rest.service.impl;

import com.kcs.rest.dao.GoodsDao;
import com.kcs.rest.pojo.AddOutBill;
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
    public List<Goods> findAllGoods() {
        return goodsDao.findAllGoods();
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
    public List<AddOutBill> findAddOutBillByCategoryName(String categoryName) {
        return goodsDao.findAddOutBillByCategoryName(categoryName);
    }

    @Override
    public List<String> findTheLastItemsNameByCategoryName(String categoryName) {
        return goodsDao.findTheLastItemsNameByCategoryName(categoryName);
    }

    @Override
    public List<AddOutBill> findAddOutBillByItemsName(String itemsName) {
        return goodsDao.findAddOutBillByItemsName(itemsName);
    }

    @Override
    public Integer addGoods(String goodsName, Integer categoryID, String goodsType, String goodsUnit) {
        return goodsDao.addGoods(goodsName,categoryID,goodsType,goodsUnit);
    }

    @Override
    public List<Goods> goodsData(int front, int back) {
        return goodsDao.goodsData(front,back);
    }

    @Override
    public Goods showUpdateGoodsByID(int goodsID) {
        return goodsDao.showUpdateGoodsByID(goodsID);
    }

    @Override
    public Integer updateGoods(Goods goods) {
        return goodsDao.updateGoods(goods);
    }

    @Override
    public Integer delGoods(Goods goods) {
        return goodsDao.delGoods(goods);
    }

    @Override
    public Integer countGoodsData() {
        return goodsDao.countGoodsData();
    }

    @Override
    public Category findCategoryNameByID(int categoryID) {
        return goodsDao.findCategoryNameByID(categoryID);
    }
}
