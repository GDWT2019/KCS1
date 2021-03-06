package com.kcs.portal.service.impl;


import com.kcs.portal.service.ProviderService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Provider;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
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

    @Override
    public List<Provider> providerData(int before, int after) {
        HashMap<String, String> param = new HashMap<>();
        param.put("front",before+"");
        param.put("back",after+"");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"provider/providerDataPage",param);
            KcsResult result = KcsResult.formatToList(s,Provider.class);
            if (result.getStatus() == 200) {
                return (List<Provider>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Provider showUpdateProviderByID(int providerID) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"provider/showUpdateProviderByID"+providerID);
            KcsResult result = KcsResult.formatToPojo(s,Provider.class);
            if (result.getStatus() == 200) {
                Provider provider = (Provider) result.getData();
                return provider;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateProvider(Provider provider) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPostJson(Rest.rest+"provider/updateProvider", JsonUtils.objectToJson(provider));
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
    public int delProvider(Provider provider) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPostJson(Rest.rest+"provider/delProvider", JsonUtils.objectToJson(provider));
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
    public int countProviderData() {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"provider/countProviderData");
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
    public Provider findOtherProvider(Integer providerID, String providerName) {

        HashMap<String, String> param = new HashMap<>();
        param.put("providerID",providerID+"");
        param.put("providerName",providerName);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"provider/findOtherProvider",param);
            KcsResult result = KcsResult.formatToPojo(s,Provider.class);
            if (result.getStatus() == 200) {
                return (Provider) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

}
