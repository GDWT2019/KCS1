package com.kcs.rest.pojo;

import java.util.Date;

/**
 * 该类用于呈现出库明细，是出库表，出库物品表，物品表，部门表，类别表，汇总表 的集合
 */
public class OutBillPresent {

    private Integer OutBillID;      //出库清单id

    private Date OutTime;           //出库时间

    private Integer CheckStatus;    //审批状态  0 待审批；1 审批通过；2 审批未通过

    private Date CheckTime;         //审批时间

    private String CheckMessage;    //审批建议

    private Date OperateTime;       //操作时间

    private Double AllTotal;        //合计金额

    private Integer StoreManager;   //仓管员

    private Integer Taker;          //领用人

    private Integer Checker;        //审批人

    private Integer TableMaker;     //制表人

    private Integer Operator;       //操作人

    private String StorePosition;   //仓库位置

    private String Remark;            //备注

    private Integer ItemsOutID;     //出库物品id

    private Integer DepartmentID;       //部门id

    private String DepartmentName;      //部门名称

    private Integer GoodsID;        //物品id

    private Integer ItemNum;        //物品数量

    private Double ItemPrice;       //物品单价

    private Double ItemTotal;       //金额（数量*单价）

    private String Project;         //项目

    private Integer CategoryID;     //类别id

    private String ItemsName;       //物品名称

    private String ItemsType;       //物品规格

    private String ItemsUnit;       //物品单位

    private String CategoryName;     //类别名称

    private Integer SummaryID;      //汇总id

    private Integer PreAmount;      //上月结存数量

    private Double PrePrice;        //上月结存价格

    private Double PreTotal;        //上月结存金额

    private Integer InAmount;       //本月入库数量

    private Double InPrice;         //本月入库价格

    private Double InTotal;         //本月金额

    private Integer OutAmount;      //本月出库数量

    private Double OutPrice;        //本月出库价格

    private Double OutTotal;        //本月出库金额

    private Integer ThisAmount;     //本月结存数量

    private Double ThisPrice;       //本月结存价格

    private Double ThisTotal;       //本月结存金额

    private String Time;            //当前月

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
        return Remark;
    }

    public void setNote(String note) {
        Remark = note;
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

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
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

    public Integer getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Integer categoryID) {
        CategoryID = categoryID;
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

    public Integer getSummaryID() {
        return SummaryID;
    }

    public void setSummaryID(Integer summaryID) {
        SummaryID = summaryID;
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
