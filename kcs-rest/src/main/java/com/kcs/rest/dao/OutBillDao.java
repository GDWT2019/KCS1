package com.kcs.rest.dao;

import com.kcs.rest.pojo.OutBill;

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

}

