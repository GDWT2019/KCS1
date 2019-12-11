package com.kcs.rest.service;

import com.kcs.rest.pojo.OutBillPresent;

import java.util.List;

public interface OutBillPresentService {
    List<OutBillPresent> findAllOutBillPresent();
    List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID);
}
