package com.kcs.rest.service;

import com.kcs.rest.pojo.OutBillPresent;

import java.util.List;

public interface OutBillPresentService {
    List<OutBillPresent> findAllOutBillPresent(int begin, int end);
    List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID);
}
