package com.kcs.portal.service.impl;

import com.kcs.portal.service.ItemInService;
import com.kcs.rest.pojo.*;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import com.kcs.rest.utils.Rest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("itemInService")
public class ItemInServiceImpl implements ItemInService {
    @Override
    public void insertNewItem(ItemIn itemIn) {
        try {
            HttpClientUtil.doPostJson(Rest.rest+"itemIn/insertNewItem", JsonUtils.objectToJson(itemIn));
//            System.out.println("jsonutils:  "+ JsonUtils.objectToJson(itemIn));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ItemsShow> findItemsInData(String inBillID) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"itemIn/findItemsInData" + inBillID);
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
            HttpClientUtil.doGet(Rest.rest+"itemIn/delItem"+itemsInID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateCheckStatus(InBill inBill) {
        try {
            HttpClientUtil.doPostJson(Rest.rest+"itemIn/UpdateCheckStatus", JsonUtils.objectToJson(inBill));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<InBill> valueIDandTime(String inBillID) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"itemIn/valueIDandTime" + inBillID);
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
            String s = HttpClientUtil.doGet(Rest.rest+"itemIn/getItemsInList" + inBillID);
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
            HttpClientUtil.doGet(Rest.rest+"itemIn/delItemByInBillID"+inBillID);
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
            HttpClientUtil.doGet(Rest.rest+"itemIn/delItemByInBillIDandGoodsID",param);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ItemIn findItemsInByItemsID(String itemsInID) {
        try {
            String s = HttpClientUtil.doPost(Rest.rest+"itemIn/findItemsInByItemsID"+itemsInID);
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
            String s = HttpClientUtil.doGet(Rest.rest+"itemIn/findItemsIdByInBillID"+inBillID);
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
            String s = HttpClientUtil.doGet(Rest.rest+"itemIn/findSumItemNumBygoodsIdAndInBillID",param);
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
