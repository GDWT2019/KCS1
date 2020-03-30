package com.kcs.portal.service.impl;

import com.kcs.portal.service.ItemInService;
import com.kcs.rest.pojo.*;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("itemInService")
public class ItemInServiceImpl implements ItemInService {
    @Override
    public void insertNewItem(ItemIn itemIn) {
        try {
            HttpClientUtil.doPostJson("http://localhost:8081/kcs_rest_war/itemIn/insertNewItem", JsonUtils.objectToJson(itemIn));
//            System.out.println("jsonutils:  "+ JsonUtils.objectToJson(itemIn));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ItemsShow> findItemsInData(String inBillID) {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/itemIn/findItemsInData" + inBillID);
            KcsResult result = KcsResult.formatToList(s,ItemsShow.class);
            if (result.getStatus() == 200) {
                return (List<ItemsShow>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delItem(String itemsInID) {
        try {
            HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/itemIn/delItem"+itemsInID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateCheckStatus(InBill inBill) {
        try {
            HttpClientUtil.doPostJson("http://localhost:8081/kcs_rest_war/itemIn/UpdateCheckStatus", JsonUtils.objectToJson(inBill));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<InBill> valueIDandTime(String inBillID) {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/itemIn/valueIDandTime" + inBillID);
            KcsResult result = KcsResult.formatToList(s,InBill.class);
            if (result.getStatus() == 200) {
                return (List<InBill>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GoodsAndCategoryAndItemsIn> getItemsInList(String inBillID) {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/itemIn/getItemsInList" + inBillID);
            KcsResult result = KcsResult.formatToList(s,GoodsAndCategoryAndItemsIn.class);
            if (result.getStatus() == 200) {
                return (List<GoodsAndCategoryAndItemsIn>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delItemByInBillID(String inBillID) {
        try {
            HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/itemIn/delItemByInBillID"+inBillID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delItemByInBillIDandGoodsID(String inBillID, Integer goodsID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("inBillID",inBillID);
        try {
            HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/itemIn/delItemByInBillIDandGoodsID",param);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ItemIn findItemsInByItemsID(String itemsInID) {
        try {
            String s = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/itemIn/findItemsInByItemsID"+itemsInID);
            KcsResult result = KcsResult.formatToPojo(s, ItemIn.class);
            if (result.getStatus() == 200) {
                ItemIn itemIn = (ItemIn) result.getData();
                return itemIn;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemIn> findItemsIdByInBillID(String inBillID) {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/itemIn/findItemsIdByInBillID"+inBillID);
            KcsResult result = KcsResult.formatToList(s,ItemIn.class);
            if (result.getStatus() == 200) {
                return (List<ItemIn>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer findSumItemNumBygoodsIdAndInBillID(Integer goodsID, String inBillID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("inBillID",inBillID);
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/itemIn/findSumItemNumBygoodsIdAndInBillID",param);
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
