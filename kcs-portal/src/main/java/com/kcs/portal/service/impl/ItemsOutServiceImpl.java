package com.kcs.portal.service.impl;

import com.kcs.portal.service.ItemsOutService;
import com.kcs.rest.pojo.ItemsOut;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.OutBill;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemsOutService")
public class ItemsOutServiceImpl implements ItemsOutService {

    @Override
    public Integer insertItemsOut(ItemsOut itemsOut) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPostJson("http://localhost:8081/kcs_rest_war/itemsOut/insertItemsOut",JsonUtils.objectToJson(itemsOut));
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
    public Integer delItemsOutByID(int itemsOutID) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/itemsOut/delItemsOut"+itemsOutID);
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
    public List<ItemsOut> findItemsOutByOutBillID(int outBillID) {
        try {
            String s = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/itemsOut/getItemsOutByOutBillID"+outBillID);
            KcsResult result = KcsResult.formatToList(s,ItemsOut.class);
            if (result.getStatus() == 200) {
                return (List<ItemsOut>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateItemsOut(ItemsOut itemsOut) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPostJson("http://localhost:8081/kcs_rest_war/itemsOut/updateItemsOut",JsonUtils.objectToJson(itemsOut));
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
    public void delItemByOutBillID(Integer outBill) {
        try {
            HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/itemsOut/delItemByOutBillID"+outBill);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
