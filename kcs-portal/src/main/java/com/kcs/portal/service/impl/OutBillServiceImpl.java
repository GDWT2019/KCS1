package com.kcs.portal.service.impl;

import com.kcs.portal.service.OutBillService;
import com.kcs.rest.pojo.*;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import com.kcs.rest.utils.Rest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service("addOutBillService")
public class OutBillServiceImpl implements OutBillService {

    @Override
    public List<Goods> getAllGoodsInSummaryGoodsId() {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"outBill/getAllGoodsInSummaryGoodsId");
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
            String s = HttpClientUtil.doGet(Rest.rest+"outBill/getAllSummary");
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
            String s = HttpClientUtil.doGet(Rest.rest+"outBill/getAllDepartment");
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

    @Override
    public List<OutBillPresent> getAllOutBillPresent(int begin, int end,String time1,String time2,String itemName,int checkStatus,int userID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("begin",begin+ "");
        param.put("end",end+ "");
        param.put("time1",time1);
        param.put("time2",time2);
        param.put("itemName",itemName);
        param.put("checkStatus",checkStatus+"");
        param.put("userID",userID+"");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"outBill/outBillPresent",param);
            KcsResult result = KcsResult.formatToList(s, OutBillPresent.class);
            if (result.getStatus() == 200) {
                List<OutBillPresent> outBillPresentList = (List<OutBillPresent>) result.getData();
                return outBillPresentList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer outBillPresentCount(String time1, String time2, String itemName ,int checkStatus,int userID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("time1",time1);
        param.put("time2",time2);
        param.put("itemName",itemName);
        param.put("checkStatus",checkStatus+"");
        param.put("userID",userID+"");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"outBill/outBillPresentCount",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                Integer count = (Integer) result.getData();
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insertOutBill(OutBill outBill) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPostJson(Rest.rest+"outBill/insertOutBill", JsonUtils.objectToJson(outBill));
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                i = (Integer) result.getData();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID) {
        try {
            String s = HttpClientUtil.doPost(Rest.rest+"outBill/getOutBillPresentByOutBillID"+outBillID);
            KcsResult result = KcsResult.formatToList(s, OutBillPresent.class);
            if (result.getStatus() == 200) {
                List<OutBillPresent> outBillPresentList = (List<OutBillPresent>) result.getData();
                return outBillPresentList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer findTheMaxOutBillID() {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPost(Rest.rest+"outBill/getTheMaxOutBillID");
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                i = (Integer) result.getData();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public Integer updateCheckByOutBillID(OutBill outBill) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPostJson(Rest.rest+"outBill/updateCheckByOutBillID",JsonUtils.objectToJson(outBill));
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                i = (Integer) result.getData();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public Integer updateOutBill(OutBill outBill) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPostJson(Rest.rest+"outBill/updateOutBill",JsonUtils.objectToJson(outBill));
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                i = (Integer) result.getData();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<OutBillPresent> ItemOutRecord(int before, int after, int goodsID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("before",before+ "");
        param.put("after",after+ "");
        param.put("goodsID",goodsID+ "");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"outBill/ItemOutRecord",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (List<OutBillPresent>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int CountItemOutRecord(int goodsID) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"outBill/CountItemOutRecord"+goodsID);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (int) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OutBillPresent> findAllOutBillPresentPrint() {
        try {
            String s = HttpClientUtil.doPost(Rest.rest+"outBill/outBillPresentPrint");
            KcsResult result = KcsResult.formatToList(s,OutBillPresent.class);
            if (result.getStatus() == 200) {
                return (List<OutBillPresent>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
