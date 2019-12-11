package com.kcs.rest.service.impl;

import com.kcs.rest.dao.SummaryDao;
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


}
