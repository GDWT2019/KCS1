package com.kcs.rest.pojo;

import java.util.Date;

public class OutBill {
    private Integer OutBillID;

    private Date OutTime;

    private Integer CheckStatus;

    private Date CheckTime;

    private String CheckMessage;

    private Date OperateTime;

    private Double AllTotal;

    private Integer StoreManager;

    private Integer Taker;

    private Integer Checker;

    private Integer TableMaker;

    private Integer Operator;

    private String StorePosition;

    private String Note;

    public Integer getOutBillID() {
        return OutBillID;
    }

    public void setOutBillID(Integer outBillID) {
        OutBillID = outBillID;
    }

    public Date getOutTime() {
        return OutTime;
    }

    public void setOutTime(Date outTime) {
        OutTime = outTime;
    }

    public Integer getCheckStatus() {
        return CheckStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        CheckStatus = checkStatus;
    }

    public Date getCheckTime() {
        return CheckTime;
    }

    public void setCheckTime(Date checkTime) {
        CheckTime = checkTime;
    }

    public String getCheckMessage() {
        return CheckMessage;
    }

    public void setCheckMessage(String checkMessage) {
        CheckMessage = checkMessage;
    }

    public Date getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(Date operateTime) {
        OperateTime = operateTime;
    }

    public Double getAllTotal() {
        return AllTotal;
    }

    public void setAllTotal(Double allTotal) {
        AllTotal = allTotal;
    }

    public Integer getStoreManager() {
        return StoreManager;
    }

    public void setStoreManager(Integer storeManager) {
        StoreManager = storeManager;
    }

    public Integer getTaker() {
        return Taker;
    }

    public void setTaker(Integer taker) {
        Taker = taker;
    }

    public Integer getChecker() {
        return Checker;
    }

    public void setChecker(Integer checker) {
        Checker = checker;
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

    public String getStorePosition() {
        return StorePosition;
    }

    public void setStorePosition(String storePosition) {
        StorePosition = storePosition;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}