package com.kcs.portal.service.impl;

import com.kcs.portal.service.InBillService;
import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.inBillShow;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("inBillService")
public class InBillServiceImpl implements InBillService {
    @Override
    public List<InBill> findAllInBill() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/inBill/inBillData");
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (List<InBill>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int count(){
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/inBill/inBillTotal");
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
    public Integer insertNewBill(InBill inBill) {
        try {
            String s = HttpClientUtil.doPostJson("http://localhost:8081/kcs_rest_war/inBill/insertNewBill", JsonUtils.objectToJson(inBill));
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (Integer)result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //显示入库的信息
    @Override
    public List<inBillShow> findInBillShow() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/inBill/inBillShowData");
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (List<inBillShow>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<inBillShow> PageInBillShow(int before, int after) {
        HashMap<String, String> param = new HashMap<>();
        param.put("before",before+ "");
        param.put("after",after+ "");
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/inBill/inBillShowPage",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (List<inBillShow>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countShow() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/inBill/inBillShowTotal");
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
    public int findMaxInBillID() {
        try {
            String s = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/inBill/findMaxInBillID");
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
    public InBill findCheckMessageByID(String inBillID) {
        try {
            String s = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/inBill/findCheckMessageByID"+inBillID);
            KcsResult result = KcsResult.formatToPojo(s, InBill.class);
            if (result.getStatus() == 200) {
                InBill inBill = (InBill) result.getData();
                return inBill;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //修改入库单除商品外的数据
    @Override
    public void updateInBillByID(InBill inBill) {
        try {
            String s = HttpClientUtil.doPostJson("http://localhost:8081/kcs_rest_war/inBill/UpdateInBill", JsonUtils.objectToJson(inBill));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
