package com.kcs.portal.service;

import com.kcs.rest.pojo.SummartAndGoodsAndCategory;
import com.kcs.rest.pojo.Summary;

import java.util.List;

public interface SummaryService {
    //根据物品id和日期查找汇总记录
    Summary findSummaryByGoodsIDAndDate(Summary summary);

    //根据时间查找GoodsID
    List<Integer> findGoodsIDByTime(String Time);

    //根据物品id，在所有最新物品的记录中查找该物品对应的汇总
    Summary findSummaryInTheLastGoodsDataByGoodsID(int GoodsID);

    List<SummartAndGoodsAndCategory> findAllTime();

    List<SummartAndGoodsAndCategory> summartyBillData(int before, int after, String time);

    int countSummary(String time);

    List<SummartAndGoodsAndCategory> summartyAllData();

    int countAll();

    List<SummartAndGoodsAndCategory> summaryAllCurrentdata(int before, int after,String itemName);

    int countReload(String itemName);


}
