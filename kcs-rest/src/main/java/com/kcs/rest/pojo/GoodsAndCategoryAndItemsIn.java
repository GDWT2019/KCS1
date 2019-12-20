package com.kcs.rest.pojo;

/**
 * 用户表物品表和类别表的结合
 */

public class GoodsAndCategoryAndItemsIn {


    private Integer ItemsInID;
    private Integer InBillID;
    private Integer GoodsID;
    private Integer CategoryID;
    private String Type;
    private Double ItemPrice;
    private Integer ItemNum;
    private Double ItemTotal;
    private String StorePosition;
    private Integer ProviderID;
    private String Note;


    private String ItemsName;       //物品名称

    private String ItemsType;       //物品规格

    private String ItemsUnit;

    private String CategoryName;

    @Override
    public String toString() {
        return "GoodsAndCategoryAndItemsIn{" +
                "ItemsInID=" + ItemsInID +
                ", InBillID=" + InBillID +
                ", GoodsID=" + GoodsID +
                ", CategoryID=" + CategoryID +
                ", Type='" + Type + '\'' +
                ", ItemPrice=" + ItemPrice +
                ", ItemNum=" + ItemNum +
                ", ItemTotal=" + ItemTotal +
                ", StorePosition='" + StorePosition + '\'' +
                ", ProviderID=" + ProviderID +
                ", Note='" + Note + '\'' +
                ", ItemsName='" + ItemsName + '\'' +
                ", ItemsType='" + ItemsType + '\'' +
                ", ItemsUnit='" + ItemsUnit + '\'' +
                ", CategoryName='" + CategoryName + '\'' +
                '}';
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

    public Integer getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(Integer goodsID) {
        GoodsID = goodsID;
    }

    public Integer getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Integer categoryID) {
        CategoryID = categoryID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        ItemPrice = itemPrice;
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

    public String getStorePosition() {
        return StorePosition;
    }

    public void setStorePosition(String storePosition) {
        StorePosition = storePosition;
    }

    public Integer getProviderID() {
        return ProviderID;
    }

    public void setProviderID(Integer providerID) {
        ProviderID = providerID;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getItemsName() {
        return ItemsName;
    }

    public void setItemsName(String itemsName) {
        ItemsName = itemsName;
    }

    public String getItemsType() {
        return ItemsType;
    }

    public void setItemsType(String itemsType) {
        ItemsType = itemsType;
    }

    public String getItemsUnit() {
        return ItemsUnit;
    }

    public void setItemsUnit(String itemsUnit) {
        ItemsUnit = itemsUnit;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
