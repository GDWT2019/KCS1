package com.kcs.portal.service;

import com.kcs.rest.pojo.*;

import java.util.List;

public interface OutBillService {
    List<Goods> getAllGoodsInSummaryGoodsId();
    List<Summary> getAllSummary();
    List<Department> getAllDepartment();
    List<OutBillPresent> getAllOutBillPresent();
    Integer insertOutBill(OutBill outBill);
    //根据outBillID查找
    List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID);
    //查找最大的OutBillID
    Integer findTheMaxOutBillID();
}
