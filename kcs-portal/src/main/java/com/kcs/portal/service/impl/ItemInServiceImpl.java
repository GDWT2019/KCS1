package com.kcs.portal.service.impl;

import com.kcs.portal.service.ItemInService;
import com.kcs.rest.pojo.ItemIn;
import com.kcs.rest.pojo.ItemsShow;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import org.springframework.stereotype.Service;

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
            KcsResult result = KcsResult.format(s);
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
}
