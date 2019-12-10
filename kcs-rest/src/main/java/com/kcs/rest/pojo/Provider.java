package com.kcs.rest.pojo;

public class Provider {
    private Integer ProviderID;
    private String ProviderName;
    private String ProviderAddress;
    private String Tel;

    public Integer getProviderID() {
        return ProviderID;
    }

    public void setProviderID(Integer providerID) {
        ProviderID = providerID;
    }

    public String getProviderName() {
        return ProviderName;
    }

    public void setProviderName(String providerName) {
        ProviderName = providerName;
    }

    public String getProviderAddress() {
        return ProviderAddress;
    }

    public void setProviderAddress(String providerAddress) {
        ProviderAddress = providerAddress;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "ProviderID=" + ProviderID +
                ", ProviderName='" + ProviderName + '\'' +
                ", ProviderAddress='" + ProviderAddress + '\'' +
                ", Tel='" + Tel + '\'' +
                '}';
    }
}
