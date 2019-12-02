package com.kcs.portal.service;

import com.kcs.rest.pojo.Department;
import com.kcs.rest.pojo.Goods;
import com.kcs.rest.pojo.OutBillPresent;
import com.kcs.rest.pojo.Summary;

import java.util.List;

public interface OutBillService {
    List<Goods> getAllGoodsInSummaryGoodsId();
    List<Summary> getAllSummary();
    List<Department> getAllDepartment();
    List<OutBillPresent> getAllOutBillPresent();
}
