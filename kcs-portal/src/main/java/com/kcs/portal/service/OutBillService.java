package com.kcs.portal.service;

import com.kcs.rest.pojo.*;

import java.util.List;

public interface OutBillService {
    List<Goods> getAllGoodsInSummaryGoodsId();
    List<Summary> getAllSummary();
    List<Department> getAllDepartment();
    List<OutBillPresent> getAllOutBillPresent(int begin, int end,String time1,String time2,String itemName);
    Integer outBillPresentCount(String time1, String time2, String itemName);
    Integer insertOutBill(OutBill outBill);
    //根据outBillID查找
    List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID);
    //查找最大的OutBillID
    Integer findTheMaxOutBillID();
    //更新审批信息
    Integer updateCheckByOutBillID(OutBill outBill);

    //更新出库单信息，若字段未null，这不更新该字段
    Integer updateOutBill(OutBill outBill);

    List<OutBillPresent> ItemOutRecord(int before, int after, int goodsID);

    int CountItemOutRecord(int goodsID);

}
