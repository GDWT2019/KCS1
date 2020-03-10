package com.kcs.rest.service.impl;

import com.kcs.rest.dao.ProviderDao;
import com.kcs.rest.pojo.Provider;
import com.kcs.rest.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("providerService")

public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderDao providerDao;

    @Override
    public List<Provider> findAllProvider() {
        return providerDao.findAllProvider();
    }

    @Override
    public Integer addProvider(String providerName, String providerAddress, String tel) {
        return providerDao.addProvider(providerName,providerAddress,tel);
    }
}
