package com.kcs.rest.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

public class SummartAndGoodsAndCategory implements Serializable {

    private Integer goodsID;
    @Excel(name = "类别", groupName = "物品", orderNum = "0")
    private String categoryName;
    @Excel(name = "品名", groupName = "物品", orderNum = "1")
    private String itemsName;
    @Excel(name = "规格", groupName = "物品", orderNum = "2")
    private String itemsType;
    @Excel(name = "数量", groupName = "上月结存", orderNum = "3")
    private Integer preAmount;
    @Excel(name = "单价", groupName = "上月结存", orderNum = "4")
    private Double prePrice;
    @Excel(name = "金额", groupName = "上月结存", orderNum = "5")
    private Double preTotal;
    @Excel(name = "数量", groupName = "本月入库", orderNum = "6")
    private Integer inAmount;
    @Excel(name = "单价", groupName = "本月入库", orderNum = "7")
    private Double inPrice;
    @Excel(name = "金额", groupName = "本月入库", orderNum = "8")
    private Double inTotal;
    @Excel(name = "数量", groupName = "本月出库", orderNum = "9")
    private Integer outAmount;
    @Excel(name = "单价", groupName = "本月出库", orderNum = "10")
    private Double outPrice;
    @Excel(name = "金额", groupName = "本月出库", orderNum = "11")
    private Double outTotal;
    @Excel(name = "数量", groupName = "本月结存", orderNum = "12")
    private Integer thisAmount;
    @Excel(name = "单价", groupName = "本月结存", orderNum = "13")
    private Double thisPrice;
    @Excel(name = "金额", groupName = "本月结存", orderNum = "14")
    private Double thisTotal;
    @Excel(name = "时间")
    private String time;

    public Integer getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(Integer goodsID) {
        this.goodsID = goodsID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getItemsType() {
        return itemsType;
    }

    public void setItemsType(String itemsType) {
        this.itemsType = itemsType;
    }

    public Integer getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(Integer preAmount) {
        this.preAmount = preAmount;
    }

    public Double getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(Double prePrice) {
        this.prePrice = prePrice;
    }

    public Double getPreTotal() {
        return preTotal;
    }

    public void setPreTotal(Double preTotal) {
        this.preTotal = preTotal;
    }

    public Integer getInAmount() {
        return inAmount;
    }

    public void setInAmount(Integer inAmount) {
        this.inAmount = inAmount;
    }

    public Double getInPrice() {
        return inPrice;
    }

    public void setInPrice(Double inPrice) {
        this.inPrice = inPrice;
    }

    public Double getInTotal() {
        return inTotal;
    }

    public void setInTotal(Double inTotal) {
        this.inTotal = inTotal;
    }

    public Integer getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(Integer outAmount) {
        this.outAmount = outAmount;
    }

    public Double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(Double outPrice) {
        this.outPrice = outPrice;
    }

    public Double getOutTotal() {
        return outTotal;
    }

    public void setOutTotal(Double outTotal) {
        this.outTotal = outTotal;
    }

    public Integer getThisAmount() {
        return thisAmount;
    }

    public void setThisAmount(Integer thisAmount) {
        this.thisAmount = thisAmount;
    }

    public Double getThisPrice() {
        return thisPrice;
    }

    public void setThisPrice(Double thisPrice) {
        this.thisPrice = thisPrice;
    }

    public Double getThisTotal() {
        return thisTotal;
    }

    public void setThisTotal(Double thisTotal) {
        this.thisTotal = thisTotal;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
