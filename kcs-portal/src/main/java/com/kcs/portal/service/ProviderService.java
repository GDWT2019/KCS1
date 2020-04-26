package com.kcs.portal.service;

import com.kcs.rest.pojo.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> findAllProvider();

    Integer addProvider(String providerName, String providerAddress, String tel);

    Provider findProviderByName(String providerName);

    List<Provider> providerData(int before, int after);

    Provider showUpdateProviderByID(int providerID);

    int updateProvider(Provider provider);

    int delProvider(Provider provider);

    int countProviderData();

}
