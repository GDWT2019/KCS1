package com.kcs.rest.pojo;

import java.io.Serializable;

/**
 * 入库明细表字段    入库物品表和用户表和入库明细表的集合
 */

public class inBillShow implements Serializable {
    private Integer ItemsInID;
    private Integer InBillID;
    private String InvoiceID;
    private String InvoiceTime;
    private Integer GoodsID;
    private String TimeIn;
    private String ItemsName;
    private String Type;
    private String StorePosition;
    private Integer ItemNum;
    private Double ItemTotal;
    private Double ItemPrice;
    private Double AllTotal;
    private String UserName;
    private Integer CheckStatus;
    private String Note;
    private Double TaxTotal;
    private String Image;

    @Override
    public String toString() {
        return "inBillShow{" +
                "ItemsInID=" + ItemsInID +
                ", InBillID=" + InBillID +
                ", InvoiceID='" + InvoiceID + '\'' +
                ", InvoiceTime='" + InvoiceTime + '\'' +
                ", GoodsID=" + GoodsID +
                ", TimeIn='" + TimeIn + '\'' +
                ", ItemsName='" + ItemsName + '\'' +
                ", Type='" + Type + '\'' +
                ", StorePosition='" + StorePosition + '\'' +
                ", ItemNum=" + ItemNum +
                ", ItemTotal=" + ItemTotal +
                ", ItemPrice=" + ItemPrice +
                ", AllTotal=" + AllTotal +
                ", UserName='" + UserName + '\'' +
                ", CheckStatus=" + CheckStatus +
                ", Note='" + Note + '\'' +
                ", TaxTotal=" + TaxTotal +
                ", Image='" + Image + '\'' +
                '}';
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Double getTaxTotal() {
        return TaxTotal;
    }

    public void setTaxTotal(Double taxTotal) {
        TaxTotal = taxTotal;
    }

    public String getInvoiceTime() {
        return InvoiceTime;
    }

    public void setInvoiceTime(String invoiceTime) {
        InvoiceTime = invoiceTime;
    }

    public Integer getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(Integer goodsID) {
        GoodsID = goodsID;
    }

    public Integer getItemsInID() {
        return ItemsInID;
    }

    public void setItemsInID(Integer itemsInID) {
        ItemsInID = itemsInID;
    }

    public Integer getInBillID() {
        return InBillID;
    }

    public void setInBillID(Integer inBillID) {
        InBillID = inBillID;
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

    public String getItemsName() {
        return ItemsName;
    }

    public void setItemsName(String itemsName) {
        ItemsName = itemsName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getStorePosition() {
        return StorePosition;
    }

    public void setStorePosition(String storePosition) {
        StorePosition = storePosition;
    }

    public Integer getItemNum() {
        return ItemNum;
    }

    public void setItemNum(Integer itemNum) {
        ItemNum = itemNum;
    }

    public Double getItemTotal() {
        return ItemTotal;
    }

    public void setItemTotal(Double itemTotal) {
        ItemTotal = itemTotal;
    }

    public Double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        ItemPrice = itemPrice;
    }

    public Double getAllTotal() {
        return AllTotal;
    }

    public void setAllTotal(Double allTotal) {
        AllTotal = allTotal;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Integer getCheckStatus() {
        return CheckStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        CheckStatus = checkStatus;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
