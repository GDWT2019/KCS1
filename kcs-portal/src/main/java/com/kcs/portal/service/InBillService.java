package com.kcs.portal.service;

import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.inBillShow;

import java.util.List;

public interface InBillService {
    List<InBill> findAllInBill();

    int count();

    Integer insertNewBill(InBill inBill);

    List<inBillShow> findInBillShow();

    List<inBillShow> PageInBillShow(int before, int after);

    int countShow();

    int findMaxInBillID();



    InBill findCheckMessageByID(String inBillID);

    void updateInBillByID(InBill inBill);
}
