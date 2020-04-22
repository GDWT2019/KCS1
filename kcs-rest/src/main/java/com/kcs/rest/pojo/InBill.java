package com.kcs.rest.pojo;

import java.io.Serializable;

public class InBill implements Serializable {
    private Integer InBillID;
    private Integer ProviderID;
    private String InvoiceID;
    private Integer StoreManager;
    private String TimeIn;
    private Integer Buyer;
    private String BuyTime;
    private Double AllTotal;
    private Integer CheckStatus;
    private String CheckMessage;
    private Integer Checker;
    private String CheckTime;
    private Integer TableMaker;
    private Integer Operator;
    private String OperateTime;
    private String Note;

    public Integer getProviderID() {
        return ProviderID;
    }

    public void setProviderID(Integer providerID) {
        ProviderID = providerID;
    }

    public String getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        InvoiceID = invoiceID;
    }

    public Integer getInBillID() {
        return InBillID;
    }

    public void setInBillID(Integer inBillID) {
        InBillID = inBillID;
    }

    public Integer getStoreManager() {
        return StoreManager;
    }

    public void setStoreManager(Integer storeManager) {
        StoreManager = storeManager;
    }

    public String getTimeIn() {
        return TimeIn;
    }

    public void setTimeIn(String timeIn) {
        TimeIn = timeIn;
    }

    public Integer getBuyer() {
        return Buyer;
    }

    public void setBuyer(Integer buyer) {
        Buyer = buyer;
    }

    public String getBuyTime() {
        return BuyTime;
    }

    public void setBuyTime(String buyTime) {
        BuyTime = buyTime;
    }

    public Double getAllTotal() {
        return AllTotal;
    }

    public void setAllTotal(Double allTotal) {
        AllTotal = allTotal;
    }

    public Integer getCheckStatus() {
        return CheckStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        CheckStatus = checkStatus;
    }

    public String getCheckMessage() {
        return CheckMessage;
    }

    public void setCheckMessage(String checkMessage) {
        CheckMessage = checkMessage;
    }

    public Integer getChecker() {
        return Checker;
    }

    public void setChecker(Integer checker) {
        Checker = checker;
    }

    public String getCheckTime() {
        return CheckTime;
    }

    public void setCheckTime(String checkTime) {
        CheckTime = checkTime;
    }

    public Integer getTableMaker() {
        return TableMaker;
    }

    public void setTableMaker(Integer tableMaker) {
        TableMaker = tableMaker;
    }

    public Integer getOperator() {
        return Operator;
    }

    public void setOperator(Integer operator) {
        Operator = operator;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
        OperateTime = operateTime;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    @Override
    public String toString() {
        return "InBill{" +
                "InBillID=" + InBillID +
                ", ProviderID=" + ProviderID +
                ", InvoiceID='" + InvoiceID + '\'' +
                ", StoreManager=" + StoreManager +
                ", TimeIn='" + TimeIn + '\'' +
                ", Buyer=" + Buyer +
                ", BuyTime='" + BuyTime + '\'' +
                ", AllTotal=" + AllTotal +
                ", CheckStatus=" + CheckStatus +
                ", CheckMessage='" + CheckMessage + '\'' +
                ", Checker=" + Checker +
                ", CheckTime='" + CheckTime + '\'' +
                ", TableMaker=" + TableMaker +
                ", Operator=" + Operator +
                ", OperateTime='" + OperateTime + '\'' +
                ", Note='" + Note + '\'' +
                '}';
    }
}
