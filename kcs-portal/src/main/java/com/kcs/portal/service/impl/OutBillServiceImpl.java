package com.kcs.portal.service.impl;

import com.kcs.portal.service.OutBillService;
import com.kcs.rest.pojo.*;
import com.kcs.rest.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("addOutBillService")
public class OutBillServiceImpl implements OutBillService {

    @Override
    public List<Goods> getAllGoodsInSummaryGoodsId() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/outBill/getAllGoodsInSummaryGoodsId");
            KcsResult result = KcsResult.formatToList(s, Goods.class);
            if (result.getStatus() == 200) {
                List<Goods> goodsList = (List<Goods>) result.getData();
                return goodsList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Summary> getAllSummary() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/outBill/getAllSummary");
            KcsResult result = KcsResult.formatToList(s, Summary.class);
            if (result.getStatus() == 200) {
                List<Summary> summaryList = (List<Summary>) result.getData();
                return summaryList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Department> getAllDepartment() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/outBill/getAllDepartment");
            KcsResult result = KcsResult.formatToList(s, Department.class);
            if (result.getStatus() == 200) {
                List<Department> departmentList = (List<Department>) result.getData();
                return departmentList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
