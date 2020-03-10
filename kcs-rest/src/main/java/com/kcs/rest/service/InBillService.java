package com.kcs.rest.service;

import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.inBillShow;

import java.util.List;

public interface InBillService {
    List<InBill> findAllInBill();

    int count();

    void insertNewBill(InBill inBill);

    List<inBillShow> inBillShowData();

    List<inBillShow> inBillShowPage(int before, int after,String time1,String time2,String itemName,Integer checkStatus);

    int countShow();

    int findMaxInBillID();

    InBill findCheckMessageByID(int inBillID);

    void UpdateInBill(InBill inBill);

    int countReload(String time1, String time2, String itemName,Integer checkStatus);

    List<inBillShow> ItemInRecord(int front, int back, int goodsId);

    int CountItemInRecord(int goodsid);
}
