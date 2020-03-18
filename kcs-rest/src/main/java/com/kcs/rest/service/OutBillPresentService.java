package com.kcs.rest.service;

import com.kcs.rest.pojo.OutBillPresent;

import java.util.List;

public interface OutBillPresentService {
    List<OutBillPresent> findAllOutBillPresent(int begin, int end,String time1,String time2,String itemName,int checkStatus,int userID);
    List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID);
    //查找总数
    Integer outBillPresentCount(String time1, String time2, String itemName,int checkStatus,int userID);
    List<OutBillPresent> findAllOutBillPresentPrint();
}
