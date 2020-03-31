package com.kcs.rest.service;

import com.kcs.rest.pojo.SummartAndGoodsAndCategory;
import com.kcs.rest.pojo.Summary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SummaryService {

    //查找汇总表所有记录
    List<Summary> findAllSummary();

    //根据期日查找GoodsID
    List<Integer> findGoodsIDByTime(String Time);

    //根据物品id和年月查找记录
    Summary findSummaryByGoodsIDAndTime(int GoodsID, String Time);

    //根据物品id，在所有最新物品的记录中查找该物品对应的汇总
    Summary findSummaryInTheLastGoodsDataByGoodsID(int GoodsID);

    //更新
    int updateSummary(Summary summary);

    List<SummartAndGoodsAndCategory> findAllTime();

    List<SummartAndGoodsAndCategory> summartyBillData(int before, int after, String time);

    int summaryTotal(String Time);

    List<SummartAndGoodsAndCategory> summartyAllData();

    int summaryAllTotal();

    List<SummartAndGoodsAndCategory> summaryAllCurrentdata(int before, int after,String time1, String time2,String itemName);

    int countReload(String time1, String time2,String itemName);

    int getThisAmount(int goodsID, String time);

    void updateSummaryToNewMonth();

    Summary findThisMonthInAmountByGoodsID(int gid, String subTime);

    Integer findAllInAmout(Integer goodsID);

    Integer findAllOutAmout(Integer goodsID);

    Integer findAllBeforeInAmout(int gid, String subTime);

    Integer findAllBeforeOutAmout(int gid, String subTime);

    Integer findAllafterInAmout(int gid, String subTime);

    Integer findAllafterOutAmout(int gid, String subTime);

    Summary findlatestAfterSummary(int gid, String subTime);

    Summary findLongestAfterSummary(int gid, String subTime);

    Integer findBetweenBeforeAndAffterOutAmout(int gid, String subTime, String subTime1);

    Integer findBetweenBeforeAndAffterInAmout(int gid, String subTime, String subTime1);
}

