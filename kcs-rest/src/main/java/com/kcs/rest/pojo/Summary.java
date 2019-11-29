package com.kcs.rest.pojo;

public class Summary {
    private Integer SummaryID;      //汇总id

    private Integer GoodsID;        //物品id

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

    public Integer getSummaryID() {
        return SummaryID;
    }

    public void setSummaryID(Integer summaryID) {
        SummaryID = summaryID;
    }

    public Integer getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(Integer goodsID) {
        GoodsID = goodsID;
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

    @Override
    public String toString() {
        return "Summary{" +
                "SummaryID=" + SummaryID +
                ", GoodsID=" + GoodsID +
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
}