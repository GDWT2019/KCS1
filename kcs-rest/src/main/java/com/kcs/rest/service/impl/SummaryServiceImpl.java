package com.kcs.rest.service.impl;

import com.kcs.rest.dao.SummaryDao;
import com.kcs.rest.pojo.SummartAndGoodsAndCategory;
import com.kcs.rest.pojo.Summary;
import com.kcs.rest.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("summaryService")
public class SummaryServiceImpl implements SummaryService{

    @Autowired
    private SummaryDao summaryDao;

    @Override
    public List<Summary> findAllSummary() {
        return summaryDao.findAllSummary();
    }

    @Override
    public List<Integer> findGoodsIDByTime(String Time) {
        return summaryDao.findGoodsIDByTime(Time);
    }

    @Override
    public Summary findSummaryByGoodsIDAndTime(int GoodsID, String Time) {
        return summaryDao.findSummaryByGoodsIDAndTime(GoodsID,Time);
    }

    @Override
    public Summary findSummaryInTheLastGoodsDataByGoodsID(int GoodsID) {
        return summaryDao.findSummaryInTheLastGoodsDataByGoodsID(GoodsID);
    }

    @Override
    public int updateSummary(Summary summary) {
        return summaryDao.updateSummary(summary);
    }

    @Override
    public List<SummartAndGoodsAndCategory> findAllTime() {
        return summaryDao.findAllTime();
    }

    @Override
    public List<SummartAndGoodsAndCategory> summartyBillData(int before, int after, String time) {
        return summaryDao.summartyBillData(before,after,time);
    }

    @Override
    public int summaryTotal(String Time) {
        return summaryDao.summaryTotal(Time);
    }

    @Override
    public List<SummartAndGoodsAndCategory> summartyAllData() {
        return summaryDao.summartyAllData();
    }

    @Override
    public int summaryAllTotal() {
        return summaryDao.summaryAllTotal();
    }

    @Override
    public List<SummartAndGoodsAndCategory> summaryAllCurrentdata(int before, int after,String itemName) {
        return summaryDao.summaryAllCurrentdata(before,after,itemName);
    }

    @Override
    public int countReload(String itemName) {
        return summaryDao.countReload(itemName);
    }

    @Override
    public int getThisAmount(int goodsID, String time) {
        return summaryDao.getThisAmount(goodsID,time);
    }


}
