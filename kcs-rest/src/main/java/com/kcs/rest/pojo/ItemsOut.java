package com.kcs.rest.pojo;

public class ItemsOut {
    private Integer ItemsOutID;

    private Integer DepartmentID;

    private Integer OutBillID;

    private Integer GoodsID;

    private Integer ItemNum;

    private Double ItemPrice;

    private Double ItemTotal;

    private String Project;

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
}