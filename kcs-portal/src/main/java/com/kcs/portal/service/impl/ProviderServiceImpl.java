package com.kcs.portal.service.impl;


import com.kcs.portal.service.ProviderService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Provider;
import com.kcs.rest.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("providerService")

public class ProviderServiceImpl implements ProviderService {



    @Override
    public List<Provider> findAllProvider() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/provider/providerData");
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {

                return (List<Provider>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}