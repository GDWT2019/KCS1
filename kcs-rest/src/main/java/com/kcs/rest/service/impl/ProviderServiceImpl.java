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

    @Override
    public Provider findProviderByName(String providerName) {
        return providerDao.findProviderByName(providerName);
    }

    @Override
    public List<Provider> providerDataPage(int front, int back) {
        return providerDao.providerDataPage(front,back);
    }

    @Override
    public Provider showUpdateProviderByID(int providerID) {
        return providerDao.showUpdateProviderByID(providerID);
    }

    @Override
    public Integer updateProvider(Provider provider) {
        return providerDao.updateProvider(provider);
    }

    @Override
    public Integer delProvider(Provider provider) {
        return providerDao.delProvider(provider);
    }

    @Override
    public Integer countProviderData() {
        return providerDao.countProviderData();
    }

    @Override
    public Provider findOtherProvider(Integer providerID, String providerName) {
        return providerDao.findOtherProvider(providerID,providerName);
    }
}