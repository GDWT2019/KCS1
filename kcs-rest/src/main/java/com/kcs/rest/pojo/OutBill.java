package com.kcs.rest.pojo;

import java.io.Serializable;
import java.util.Date;

public class OutBill  implements Serializable {
    private Integer OutBillID;      //出库清单id

    private String OutTime;           //出库时间

    private Integer CheckStatus;    //审批状态  0 待审批；1 审批通过；2 审批未通过

    private String CheckTime;         //审批时间

    private String CheckMessage;    //审批建议

    private String OperateTime;       //操作时间

    private Double AllTotal;        //合计金额

    private Integer StoreManager;   //仓管员

    private Integer Taker;          //领用人

    private Integer Checker;        //审批人

    private Integer TableMaker;     //制表人

    private Integer Operator;       //操作人

    private String Remark;            //备注

    public Integer getOutBillID() {
        return OutBillID;
    }

    public void setOutBillID(Integer outBillID) {
        OutBillID = outBillID;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }

    public Integer getCheckStatus() {
        return CheckStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        CheckStatus = checkStatus;
    }

    public String getCheckTime() {
        return CheckTime;
    }

    public void setCheckTime(String checkTime) {
        CheckTime = checkTime;
    }

    public String getCheckMessage() {
        return CheckMessage;
    }

    public void setCheckMessage(String checkMessage) {
        CheckMessage = checkMessage;
    }

    public String getOperateTime() {
        return OperateTime;
    }

    public void setOperateTime(String operateTime) {
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

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @Override
    public String toString() {
        return "OutBill{" +
                "OutBillID=" + OutBillID +
                ", OutTime='" + OutTime + '\'' +
                ", CheckStatus=" + CheckStatus +
                ", CheckTime='" + CheckTime + '\'' +
                ", CheckMessage='" + CheckMessage + '\'' +
                ", OperateTime='" + OperateTime + '\'' +
                ", AllTotal=" + AllTotal +
                ", StoreManager=" + StoreManager +
                ", Taker=" + Taker +
                ", Checker=" + Checker +
                ", TableMaker=" + TableMaker +
                ", Operator=" + Operator +
                ", Remark='" + Remark + '\'' +
                '}';
    }
}