package com.kcs.rest.pojo;

import java.io.Serializable;

public class ItemsOut implements Serializable {
    private Integer ItemsOutID;     //出库物品id

    private Integer DepartmentID;       //部门id

    private Integer OutBillID;      //出库清单id

    private Integer GoodsID;        //物品id

    private Integer ItemNum;        //物品数量

    private Double ItemPrice;       //物品单价

    private Double ItemTotal;       //金额（数量*单价）

    private String Project;         //项目

    private String Note;            //附注

    private String StorePosition;   //仓库位置

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public Integer getItemsOutID() {
        return ItemsOutID;
    }

    public void setItemsOutID(Integer itemsOutID) {
        ItemsOutID = itemsOutID;
    }

    public Integer getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        DepartmentID = departmentID;
    }

    public Integer getOutBillID() {
        return OutBillID;
    }

    public void setOutBillID(Integer outBillID) {
        OutBillID = outBillID;
    }

    public Integer getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(Integer goodsID) {
        GoodsID = goodsID;
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

    public String getProject() {
        return Project;
    }

    public void setProject(String project) {
        Project = project;
    }

    public String getStorePosition() {
        return StorePosition;
    }

    public void setStorePosition(String storePosition) {
        StorePosition = storePosition;
    }

    @Override
    public String toString() {
        return "ItemsOut{" +
                "ItemsOutID=" + ItemsOutID +
                ", DepartmentID=" + DepartmentID +
                ", OutBillID=" + OutBillID +
                ", GoodsID=" + GoodsID +
                ", ItemNum=" + ItemNum +
                ", ItemPrice=" + ItemPrice +
                ", ItemTotal=" + ItemTotal +
                ", Project='" + Project + '\'' +
                ", Note='" + Note + '\'' +
                ", StorePosition='" + StorePosition + '\'' +
                '}';
    }
}