package com.kcs.rest.service;

import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.inBillShow;

import java.util.List;

public interface InBillService {
    List<InBill> findAllInBill();

    int count();

    void insertNewBill(InBill inBill);

    List<inBillShow> inBillShowData();

    List<inBillShow> inBillShowPage(int before, int after);

    int countShow();

    int findMaxInBillID();

    InBill findCheckMessageByID(int inBillID);
}
