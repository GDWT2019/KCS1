package com.kcs.rest.dao;

import com.kcs.rest.pojo.OutBill;

public interface OutBillDao {

    //插入一条数据，并返回该id
    Integer insertOutBill(OutBill outBill);

    //根据OutBillID查找
    OutBill findOutBillByID(int outBillID);

    //查找最大的OutBillID
    Integer findTheMaxOutBillID();
}

