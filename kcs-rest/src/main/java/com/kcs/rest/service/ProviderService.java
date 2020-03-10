package com.kcs.rest.service;

import com.kcs.rest.pojo.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> findAllProvider();

    Integer addProvider(String providerName, String providerAddress, String tel);
}
