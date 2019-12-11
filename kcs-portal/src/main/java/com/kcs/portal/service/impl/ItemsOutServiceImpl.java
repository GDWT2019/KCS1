package com.kcs.portal.service.impl;

import com.kcs.portal.service.ItemsOutService;
import com.kcs.rest.pojo.ItemsOut;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import org.springframework.stereotype.Service;

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
}
