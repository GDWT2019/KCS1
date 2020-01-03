package com.kcs.rest.dao;

import com.kcs.rest.pojo.OutBill;
import com.kcs.rest.pojo.OutBillPresent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OutBillDao {

    //插入一条数据，并返回该id
    Integer insertOutBill(OutBill outBill);

    //根据OutBillID查找
    OutBill findOutBillByID(int outBillID);

    //查找最大的OutBillID
    Integer findTheMaxOutBillID();

    //根据id删除
    Integer delOutBillByOutBillID(int outBillID);

    //更新出库单信息，若字段未null，这不更新该字段
    Integer updateOutBill(OutBill outBill);

    //更新审批信息
    Integer updateCheckByOutBillID(OutBill outBill);

    List<OutBillPresent> ItemOutRecord(@Param("front")int front, @Param("back")int back, @Param("id")int id);

    int CountItemOutRecord(int goodsid);

}

