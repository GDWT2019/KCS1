package com.kcs.portal.service;

import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.inBillShow;

import java.util.List;

public interface InBillService {
    List<InBill> findAllInBill();

    int count();

    Integer insertNewBill(InBill inBill);

    List<inBillShow> findInBillShow();

    List<inBillShow> PageInBillShow(int before, int after,String time1,String time2,String itemName);

    int countShow();

    int findMaxInBillID();



    InBill findCheckMessageByID(String inBillID);

    void updateInBillByID(InBill inBill);

    int countReload(String time1, String time2, String itemName);

    List<inBillShow> ItemInRecord(int before, int after,int goodsID);

    int CountItemInRecord(int goodsID);
}
