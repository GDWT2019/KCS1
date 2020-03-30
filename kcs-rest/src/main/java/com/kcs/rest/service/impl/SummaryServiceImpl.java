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

        updateSummaryToNewMonth();

        List<SummartAndGoodsAndCategory> summartyBillData = summaryDao.summartyBillData(before, after, time);

        return summartyBillData;
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
    public List<SummartAndGoodsAndCategory> summaryAllCurrentdata(int before, int after,String time1,String time2,String itemName) {
        return summaryDao.summaryAllCurrentdata(before,after,time1,time2,itemName);
    }

    @Override
    public int countReload(String time1, String time2,String itemName) {
        return summaryDao.countReload(time1,time2,itemName);
    }

    @Override
    public int getThisAmount(int goodsID, String time) {
        return summaryDao.getThisAmount(goodsID,time);
    }

    public void updateSummaryToNewMonth(){
        //查找最新日期
        String latestTime = summaryDao.findLatestTime();
        List<Integer> AllGoodsID = summaryDao.findGoodsID();
        for (Integer goodsID : AllGoodsID) {
            //找出每个goodsID的时间最远的记录
            Summary longTimeSummary=summaryDao.findLongTimeSummary(goodsID);
            //查找最远月份与最新月相差多少个月
            String longTime = longTimeSummary.getTime() + "-1";
            Integer dateDiff = summaryDao.findDateDiff(longTime);//两个日期的差
            for (int i = 1; i <=dateDiff ; i++) {
                String dateAdd = summaryDao.dateAdd(i, longTime);
                //取前一个月的值
                String brforeMonth=summaryDao.findMonth(dateAdd+"-1");
                Summary beforeMonth = summaryDao.findBeforeMonth(goodsID, dateAdd+"-1");
                //在查找该月份有无数据
                Summary everyTimeSummary = summaryDao.findSummaryByGoodsIDAndTime(goodsID, dateAdd);
                if(everyTimeSummary==null){
                    Summary summary =new Summary();
                    summary.setGoodsID(beforeMonth.getGoodsID());
                    summary.setPreAmount(beforeMonth.getThisAmount());
                    summary.setPrePrice(beforeMonth.getThisPrice());
                    summary.setPreTotal(beforeMonth.getThisTotal());
                    summary.setInAmount(0);
                    summary.setInPrice(0.0);
                    summary.setInTotal(0.0);
                    summary.setOutAmount(0);
                    summary.setOutPrice(0.0);
                    summary.setOutTotal(0.0);
                    summary.setThisAmount(beforeMonth.getThisAmount());
                    summary.setThisPrice(beforeMonth.getThisPrice());
                    summary.setThisTotal(beforeMonth.getThisTotal());
                    summary.setTime(dateAdd);
                    summaryDao.insertSummary(summary);
                }
            }
        }
    }

    @Override
    public Summary findThisMonthInAmountByGoodsID(int gid, String subTime) {
        return summaryDao.findThisMonthInAmountByGoodsID(gid,subTime);
    }

    @Override
    public Integer findAllInAmout(Integer goodsID) {
        return summaryDao.findAllInAmout(goodsID);
    }

    @Override
    public Integer findAllOutAmout(Integer goodsID) {
        return summaryDao.findAllOutAmout(goodsID);
    }

    @Override
    public Integer findAllBeforeInAmout(int gid, String subTime) {
        return summaryDao.findAllBeforeInAmout(gid,subTime);
    }

    @Override
    public Integer findAllBeforeOutAmout(int gid, String subTime) {
        return summaryDao.findAllBeforeOutAmout(gid,subTime);
    }

    @Override
    public Integer findAllafterInAmout(int gid, String subTime) {
        return summaryDao.findAllafterInAmout(gid,subTime);
    }

    @Override
    public Integer findAllafterOutAmout(int gid, String subTime) {
        return summaryDao.findAllafterOutAmout(gid,subTime);
    }

    @Override
    public Summary findlatestAfterSummary(int gid, String subTime) {
        return summaryDao.findlatestAfterSummary(gid,subTime);
    }
}
