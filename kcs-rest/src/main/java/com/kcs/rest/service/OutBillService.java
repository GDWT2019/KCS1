package com.kcs.rest.service;

import com.kcs.rest.pojo.OutBill;

public interface OutBillService {
    //插入一条数据,并返回该id
    Integer insertOutBill(OutBill outBill);

    //查找最大的OutBillID
    Integer findTheMaxOutBillID();
}
