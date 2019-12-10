package com.kcs.rest.dao;

import com.kcs.rest.pojo.OutBillPresent;

import java.util.List;

public interface OutBillPresentDao {
    //查找所有用于展示的outBill
    List<OutBillPresent> findAllOutBillPresent();

    //根据outBillID查找
    List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID);
}
