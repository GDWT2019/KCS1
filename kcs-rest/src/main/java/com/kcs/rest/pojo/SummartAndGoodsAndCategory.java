package com.kcs.rest.pojo;

public class SummartAndGoodsAndCategory {
    private Integer GoodsID;
    private String CategoryName;
    private String ItemsName;
    private String ItemsType;
    private Integer PreAmount;
    private Double PrePrice;
    private Double PreTotal;
    private Integer InAmount;
    private Double InPrice;
    private Double InTotal;
    private Integer OutAmount;
    private Double OutPrice;
    private Double OutTotal;
    private Integer ThisAmount;
    private Double ThisPrice;
    private Double ThisTotal;
    private String Time;

    @Override
    public String toString() {
        return "SummartAndGoodsAndCategory{" +
                "GoodsID=" + GoodsID +
                ", CategoryName='" + CategoryName + '\'' +
                ", ItemsName='" + ItemsName + '\'' +
                ", ItemsType='" + ItemsType + '\'' +
                ", PreAmount=" + PreAmount +
                ", PrePrice=" + PrePrice +
                ", PreTotal=" + PreTotal +
                ", InAmount=" + InAmount +
                ", InPrice=" + InPrice +
                ", InTotal=" + InTotal +
                ", OutAmount=" + OutAmount +
                ", OutPrice=" + OutPrice +
                ", OutTotal=" + OutTotal +
                ", ThisAmount=" + ThisAmount +
                ", ThisPrice=" + ThisPrice +
                ", ThisTotal=" + ThisTotal +
                ", Time='" + Time + '\'' +
                '}';
    }

    public Integer getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(Integer goodsID) {
        GoodsID = goodsID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
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

    public Integer getPreAmount() {
        return PreAmount;
    }

    public void setPreAmount(Integer preAmount) {
        PreAmount = preAmount;
    }

    public Double getPrePrice() {
        return PrePrice;
    }

    public void setPrePrice(Double prePrice) {
        PrePrice = prePrice;
    }

    public Double getPreTotal() {
        return PreTotal;
    }

    public void setPreTotal(Double preTotal) {
        PreTotal = preTotal;
    }

    public Integer getInAmount() {
        return InAmount;
    }

    public void setInAmount(Integer inAmount) {
        InAmount = inAmount;
    }

    public Double getInPrice() {
        return InPrice;
    }

    public void setInPrice(Double inPrice) {
        InPrice = inPrice;
    }

    public Double getInTotal() {
        return InTotal;
    }

    public void setInTotal(Double inTotal) {
        InTotal = inTotal;
    }

    public Integer getOutAmount() {
        return OutAmount;
    }

    public void setOutAmount(Integer outAmount) {
        OutAmount = outAmount;
    }

    public Double getOutPrice() {
        return OutPrice;
    }

    public void setOutPrice(Double outPrice) {
        OutPrice = outPrice;
    }

    public Double getOutTotal() {
        return OutTotal;
    }

    public void setOutTotal(Double outTotal) {
        OutTotal = outTotal;
    }

    public Integer getThisAmount() {
        return ThisAmount;
    }

    public void setThisAmount(Integer thisAmount) {
        ThisAmount = thisAmount;
    }

    public Double getThisPrice() {
        return ThisPrice;
    }

    public void setThisPrice(Double thisPrice) {
        ThisPrice = thisPrice;
    }

    public Double getThisTotal() {
        return ThisTotal;
    }

    public void setThisTotal(Double thisTotal) {
        ThisTotal = thisTotal;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
