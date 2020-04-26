package com.kcs.rest.service;

import com.kcs.rest.pojo.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> findAllProvider();

    Integer addProvider(String providerName, String providerAddress, String tel);

    Provider findProviderByName(String providerName);

    List<Provider> providerDataPage(int front, int back);

    Provider showUpdateProviderByID(int providerID);

    Integer updateProvider(Provider provider);

    Integer delProvider(Provider provider);

    Integer countProviderData();

}
