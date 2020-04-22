package com.kcs.rest.pojo;

import java.io.Serializable;

/**
 * 审核时候显示的字段   入库物品表、用户表的集合
 */

public class ItemsShow implements Serializable {
    private Integer InBillID;
    private String InvoiceID;
    private String TimeIn;
    private Integer GoodsID;
    private String Type;
    private Integer ItemNum;
    private Double ItemPrice;
    private Double ItemTotal;
    private String StorePosition;
    private Integer Operator;
    private Integer StoreManager;
    private Integer Checker;
    private Integer TableMaker;
    private Integer CheckStatus;
    private Integer Buyer;

    @Override
    public String toString() {
        return "ItemsShow{" +
                "InBillID=" + InBillID +
                ", InvoiceID='" + InvoiceID + '\'' +
                ", TimeIn='" + TimeIn + '\'' +
                ", GoodsID=" + GoodsID +
                ", Type='" + Type + '\'' +
                ", ItemNum=" + ItemNum +
                ", ItemPrice=" + ItemPrice +
                ", ItemTotal=" + ItemTotal +
                ", StorePosition='" + StorePosition + '\'' +
                ", Operator=" + Operator +
                ", StoreManager=" + StoreManager +
                ", Checker=" + Checker +
                ", TableMaker=" + TableMaker +
                ", CheckStatus=" + CheckStatus +
                ", Buyer=" + Buyer +
                '}';
    }

    public String getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        InvoiceID = invoiceID;
    }

    public String getTimeIn() {
        return TimeIn;
    }

    public void setTimeIn(String timeIn) {
        TimeIn = timeIn;
    }

    public Integer getStoreManager() {
        return StoreManager;
    }

    public void setStoreManager(Integer storeManager) {
        StoreManager = storeManager;
    }

    public Integer getCheckStatus() {
        return CheckStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        CheckStatus = checkStatus;
    }

    public Integer getBuyer() {
        return Buyer;
    }

    public void setBuyer(Integer buyer) {
        Buyer = buyer;
    }

    public Integer getInBillID() {
        return InBillID;
    }

    public void setInBillID(Integer inBillID) {
        InBillID = inBillID;
    }



    public Integer getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(Integer goodsID) {
        GoodsID = goodsID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Integer getItemNum() {
        return ItemNum;
    }

    public void setItemNum(Integer itemNum) {
        ItemNum = itemNum;
    }

    public Double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        ItemPrice = itemPrice;
    }

    public Double getItemTotal() {
        return ItemTotal;
    }

    public void setItemTotal(Double itemTotal) {
        ItemTotal = itemTotal;
    }

    public String getStorePosition() {
        return StorePosition;
    }

    public void setStorePosition(String storePosition) {
        StorePosition = storePosition;
    }

    public Integer getOperator() {
        return Operator;
    }

    public void setOperator(Integer operator) {
        Operator = operator;
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

}
