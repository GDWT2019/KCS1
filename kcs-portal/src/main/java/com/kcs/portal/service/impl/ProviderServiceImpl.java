package com.kcs.portal.service.impl;


import com.kcs.portal.service.ProviderService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Provider;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.Rest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("providerService")

public class ProviderServiceImpl implements ProviderService {



    @Override
    public List<Provider> findAllProvider() {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"provider/providerData");
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {

                return (List<Provider>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer addProvider(String providerName, String providerAddress, String tel) {
        HashMap<String, String> param = new HashMap<>();
        param.put("providerName",providerName);
        param.put("providerAddress",providerAddress);
        param.put("tel",tel);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"provider/addProvider",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Provider findProviderByName(String providerName) {
        try {
            String s = HttpClientUtil.doPost(Rest.rest+"provider/findProviderByName"+providerName);
            KcsResult result = KcsResult.formatToPojo(s, Provider.class);
            if (result.getStatus() == 200) {
                Provider provider = (Provider) result.getData();
                return provider;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
