package com.kcs.rest.service;

import com.kcs.rest.pojo.OutBill;

public interface OutBillService {
    //插入一条数据,并返回该id
    Integer insertOutBill(OutBill outBill);

    //查找最大的OutBillID
    Integer findTheMaxOutBillID();

    //根据OutBillID查找
    OutBill findOutBillByID(int outBillID);

    //更新审批信息
    Integer updateCheckByOutBillID(OutBill outBill);

    //更新出库单信息，若字段未null，这不更新该字段
    Integer updateOutBill(OutBill outBill);
}
