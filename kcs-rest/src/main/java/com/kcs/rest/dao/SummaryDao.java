package com.kcs.rest.dao;

import com.kcs.rest.pojo.SummartAndGoodsAndCategory;
import com.kcs.rest.pojo.Summary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SummaryDao {

    //查找汇总表所有记录
    List<Summary> findAllSummary();

    //根据期日查找GoodsID
    List<Integer> findGoodsIDByTime(@Param("Time")String Time);

    //根据物品id和年月查找记录
    Summary findSummaryByGoodsIDAndTime(@Param("GoodsID")int GoodsID, @Param("Time") String Time);

    //根据物品id，在所有最新物品的记录中查找该物品对应的汇总
    Summary findSummaryInTheLastGoodsDataByGoodsID(int GoodsID);

    //更新
    int updateSummary(Summary summary);

    /**
     * 根据物品id和年月查找记录该时间及后面时间的所有数据
     * @param GoodsID
     * @param Time
     * @return
     */
    List<Summary> findSummaryByGoodsIDAndTimeAfter(@Param("GoodsID")int GoodsID, @Param("Time") String Time);

    List<SummartAndGoodsAndCategory> findAllTime();

    List<SummartAndGoodsAndCategory> summartyBillData(@Param("front")int before, @Param("back")int after,@Param("time") String time);

    int summaryTotal(String Time);

    Summary findBeforeMonth(@Param("goodsID")Integer goodsID, @Param("time")String time);

    void insertSummary(Summary summary1);

    void delSummaryByid(Integer summaryID);

    List<SummartAndGoodsAndCategory> summartyAllData();

    int summaryAllTotal();

    List<SummartAndGoodsAndCategory> summaryAllCurrentdata(@Param("front")int before, @Param("back")int after,@Param("itemName")String itemName);

    int countReload(@Param("itemName")String itemName);

}
