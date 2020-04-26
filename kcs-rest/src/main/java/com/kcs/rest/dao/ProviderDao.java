package com.kcs.rest.dao;

import com.kcs.rest.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderDao {
    List<Provider> findAllProvider();

    Integer addProvider(@Param("providerName") String providerName,@Param("providerAddress")String providerAddress,@Param("tel")String tel);

    Provider findProviderByName(String providerName);


    List<Provider> providerDataPage(@Param("front") int front, @Param("back") int back);

    Provider showUpdateProviderByID(int providerID);

    Integer updateProvider(Provider provider);

    Integer delProvider(Provider provider);

    Integer countProviderData();

}
