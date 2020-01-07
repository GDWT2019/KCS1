package com.kcs.rest.service.impl;

import com.kcs.rest.dao.*;
import com.kcs.rest.pojo.*;
import com.kcs.rest.service.ItemsOutService;
import com.kcs.rest.utils.LogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("itemsOutService")
public class ItemsOutServiceImpl implements ItemsOutService {

    @Autowired
    private ItemsOutDao itemsOutDao;
    @Autowired
    private OutBillDao outBillDao;
    @Autowired
    private SummaryDao summaryDao;
    @Autowired
    private ItemInDao itemInDao;
    @Autowired
    private InBillDao inBillDao;



    @LogAnno(operateType = "新增出库物品")
    @Override
    public int insertItemsOut(ItemsOut itemsOut) {
        //更新出库物品仓库位置

        Integer goodsID = itemsOut.getGoodsID();
        Integer outBillID = itemsOut.getOutBillID();

        ItemIn itemIn = itemInDao.findItemsByGoodsID(itemsOut.getGoodsID());
        if (itemIn.getStorePosition()!=null){
            itemsOut.setStorePosition(itemIn.getStorePosition());
        }
        //插入出库物品信息
        int i = itemsOutDao.insertItemsOut(itemsOut);

        String time=outBillDao.findTimeByID(outBillID);
        String subTime = time.substring(0, 7);
        if (i>0){
        Summary summary = summaryDao.findSummaryByGoodsIDAndTime(goodsID, subTime);

            //如果有数据就直接更新
            if(summary!=null){
                summary.setOutAmount(summary.getOutAmount()+itemsOut.getItemNum());
                summary.setOutTotal(summary.getOutTotal()+itemsOut.getItemTotal());
                summary.setOutPrice(new BigDecimal(summary.getOutTotal()/summary.getOutAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                summary.setThisAmount(summary.getPreAmount()+summary.getInAmount()-summary.getOutAmount());
                summary.setThisTotal(summary.getPreTotal()+summary.getInTotal()-summary.getOutTotal());
                if(summary.getThisAmount()==0){
                    summary.setThisPrice(0.0);
                }else{
                    summary.setThisPrice(new BigDecimal(summary.getThisTotal()/summary.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
                summaryDao.updateSummary(summary);
                //插入本月数据后，同步后面时间的数据
                List<Summary> timeAfter = summaryDao.findSummaryByGoodsIDAndTimeAfter(goodsID, subTime);
                for (Summary s : timeAfter) {
                    //判断是否是本月的数据，然后修改后面时间的数据
                    if(!s.getTime().equals(subTime)){
                        String nowTime = s.getTime()+"-1";
                        Summary frontSummary=summaryDao.findNearestSummaryByIdAndTime(s.getGoodsID(),s.getTime());
                        //将上月的本月结存放入到当前月的上月结存
                        s.setPreAmount(frontSummary.getThisAmount());
                        s.setPrePrice(frontSummary.getThisPrice());
                        s.setPreTotal(frontSummary.getThisTotal());
                        s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                        s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                        if(s.getThisAmount()==0){
                            s.setThisPrice(0.0);
                        }else{
                            s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        }
                        summaryDao.updateSummary(s);

                    }
                }

            }
            //没有数据就查询就查询上一个月的数据，和这个月的一起存入

            //1.3好修改到这里
            else
            {
                Summary BeforeSummary=summaryDao.findNearestSummaryByIdAndTime(goodsID,time);
                //如果存在上个月的数据，将上月的本月库存存在当前月的上月库存
                //1.2这个月的出库已经查询了，还要查这个月的入库
                //查询当月的入库数量

                List<inBillShow> PresentItemIn = inBillDao.findPresentItemIn(subTime,goodsID);
                int presentAmount = 0;
                double presentTotal=0;
                for (inBillShow inBillShow : PresentItemIn) {
                    presentAmount= presentAmount+ inBillShow.getItemNum();
                    presentTotal = presentTotal+ inBillShow.getItemTotal();
                }

                if(BeforeSummary!=null){
                    Summary summary1=new Summary();
                    summary1.setPreAmount(BeforeSummary.getThisAmount());
                    summary1.setPrePrice(BeforeSummary.getThisPrice());
                    summary1.setPreTotal(BeforeSummary.getThisTotal());
                    summary1.setGoodsID(itemsOut.getGoodsID());
                    summary1.setInAmount(presentAmount);
                    summary1.setInTotal(presentTotal);
                    if(summary1.getInAmount()==0){
                        summary1.setInPrice(0.0);
                    }else
                    {
                        summary1.setInPrice(new BigDecimal(summary1.getInTotal()/summary1.getInAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                    summary1.setOutAmount(itemsOut.getItemNum());
                    summary1.setOutTotal(itemsOut.getItemTotal());
                    summary1.setOutPrice(new BigDecimal(summary1.getOutTotal()/summary1.getOutAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    summary1.setThisAmount(summary1.getPreAmount()+summary1.getInAmount()-summary1.getOutAmount());
                    summary1.setThisTotal(summary1.getPreTotal()+summary1.getInTotal()-summary1.getOutTotal());
                    if(summary1.getThisAmount()==0){
                        summary1.setThisPrice(0.0);
                    }else {
                        summary1.setThisPrice(new BigDecimal(summary1.getThisTotal()/summary1.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                    summary1.setTime(subTime);
                    summaryDao.insertSummary(summary1);
                    List<Summary> timeAfter = summaryDao.findSummaryByGoodsIDAndTimeAfter(goodsID, subTime);
                    for (Summary s : timeAfter) {
                        //判断是否是本月的数据，然后修改后面时间的数据
                        if(!s.getTime().equals(subTime)){
                            String nowTime = s.getTime()+"-1";
                            Summary frontSummary=summaryDao.findNearestSummaryByIdAndTime(s.getGoodsID(),s.getTime());
                            //将上月的本月结存放入到当前月的上月结存
                            s.setPreAmount(frontSummary.getThisAmount());
                            s.setPrePrice(frontSummary.getThisPrice());
                            s.setPreTotal(frontSummary.getThisTotal());
                            s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                            s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                            if(s.getThisAmount()==0){
                                s.setThisPrice(0.0);
                            }else{
                                s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            }
                            summaryDao.updateSummary(s);
                        }
                    }
                }
                //如果不存在上月数据，将数据置为0并且存在当前数据
                else{

                    List<inBillShow> PresentItemIn1 = inBillDao.findPresentItemIn(subTime,goodsID);
                    int presentAmount1 = 0;
                    double presentTotal1=0;
                    for (inBillShow inBillShow : PresentItemIn) {
                        presentAmount1= presentAmount1+ inBillShow.getItemNum();
                        presentTotal1 = presentTotal1+ inBillShow.getItemTotal();
                    }

                    Summary summary2=new Summary();
                    summary2.setPreAmount(0);
                    summary2.setPrePrice(0.0);
                    summary2.setPreTotal(0.0);
                    summary2.setGoodsID(itemsOut.getGoodsID());
                    summary2.setInAmount(presentAmount1);
                    summary2.setInTotal(presentTotal1);
                    if(summary2.getInAmount()==0){
                        summary2.setInPrice(0.0);
                    }else
                    {
                        summary2.setInPrice(new BigDecimal(summary2.getInTotal()/summary2.getInAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }

                    summary2.setOutAmount(itemsOut.getItemNum());
                    summary2.setOutTotal(itemsOut.getItemTotal());
                    summary2.setOutPrice(new BigDecimal(summary2.getOutTotal()/summary2.getOutAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    summary2.setThisAmount(summary2.getPreAmount() + summary2.getInAmount()- summary2.getOutAmount());
                    summary2.setThisTotal(summary2.getPreTotal()+summary2.getInTotal()-summary2.getOutTotal());
                    if(summary2.getThisAmount()==0){
                        summary2.setThisPrice(0.0);
                    }else {
                        summary2.setThisPrice(new BigDecimal(summary2.getThisTotal()/summary2.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                    summary2.setTime(subTime);
                    summaryDao.insertSummary(summary2);
                    List<Summary> timeAfter = summaryDao.findSummaryByGoodsIDAndTimeAfter(goodsID, subTime);
                    for (Summary s : timeAfter) {
                        //判断是否是本月的数据，然后修改后面时间的数据
                        if(!s.getTime().equals(subTime)){
                            String nowTime = s.getTime()+"-1";
                            Summary frontSummary=summaryDao.findNearestSummaryByIdAndTime(s.getGoodsID(),s.getTime());
                            //将上月的本月结存放入到当前月的上月结存
                            s.setPreAmount(frontSummary.getThisAmount());
                            s.setPrePrice(frontSummary.getThisPrice());
                            s.setPreTotal(frontSummary.getThisTotal());
                            s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                            s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                            if(s.getThisAmount()==0){
                                s.setThisPrice(0.0);
                            }else{
                                s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            }
                            summaryDao.updateSummary(s);
                        }
                    }
                }
            }
        }
        return i;
    }

    /**
     * 删除出库物品时，汇总表的出库记录减少对应的数量，合计；增加本月的数量，合计；上月的数量，合计
     * @param itemsOutID
     * @return
     */
    @LogAnno(operateType = "删除出库物品")
    @Override
    public Integer delItemsOutByID(int itemsOutID) {

        //根据id，找到出库物品记录
        ItemsOut itemsOut = itemsOutDao.findItemsOutByID(itemsOutID);
        Integer outBillID = itemsOut.getOutBillID();
        String time = outBillDao.findTimeByID(outBillID);
        String subTime = time.substring(0, 7);

        //根据出库物品的outBillID，查找到出库单记录
        OutBill outBill = outBillDao.findOutBillByID(itemsOut.getOutBillID());

        try {
            Summary summary = summaryDao.findSummaryByGoodsIDAndTime(itemsOut.getGoodsID(), subTime);
            if(summary!=null){
                summary.setOutAmount(summary.getOutAmount()-itemsOut.getItemNum());
                summary.setOutTotal(summary.getOutTotal()-itemsOut.getItemTotal());
                summary.setOutPrice(new BigDecimal(summary.getThisTotal()/summary.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                summary.setThisAmount(summary.getPreAmount()+summary.getInAmount()-summary.getOutAmount());
                summary.setThisTotal(summary.getPreTotal()+summary.getInTotal()-summary.getOutTotal());
                if(summary.getThisAmount()==0){
                    summary.setThisPrice(0.0);
                }else{
                    summary.setThisPrice(new BigDecimal(summary.getThisTotal()/summary.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
                if(summary.getPreAmount()==0&&summary.getInAmount()==0&&summary.getOutAmount()==0&&summary.getThisAmount()==0){
                    summaryDao.delSummaryByid(summary.getSummaryID());
                }else {
                    summaryDao.updateSummary(summary);
                }
                //同步后面时间的数据
                List<Summary> timeAfter = summaryDao.findSummaryByGoodsIDAndTimeAfter(itemsOut.getGoodsID(), subTime);
                for (Summary s : timeAfter) {
                    //判断是否是本月的数据，然后修改后面时间的数据
                    if(!s.getTime().equals(subTime)){
                        String nowTime = s.getTime()+"-1";
                        Summary frontSummary=summaryDao.findNearestSummaryByIdAndTime(s.getGoodsID(),s.getTime());
                        //将上月的本月结存放入到当前月的上月结存
                        if(frontSummary!=null){
                            s.setPreAmount(frontSummary.getThisAmount());
                            s.setPrePrice(frontSummary.getThisPrice());
                            s.setPreTotal(frontSummary.getThisTotal());
                            s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                            s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                            if(s.getThisAmount()==0){
                                s.setThisPrice(0.0);
                            }else{
                                s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            }
                        }else{
                            s.setPreAmount(0);
                            s.setPrePrice(0.0);
                            s.setPreTotal(0.0);
                            s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                            s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                            if(s.getThisAmount()==0){
                                s.setThisPrice(0.0);
                            }else{
                                s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            }
                        }

                        if(s.getPreAmount()==0&&s.getInAmount()==0&&s.getOutAmount()==0&&s.getThisAmount()==0){
                            summaryDao.delSummaryByid(s.getSummaryID());
                        }else {
                            summaryDao.updateSummary(s);
                        }
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //修改出库单信息，并更新
            outBill.setAllTotal(outBill.getAllTotal()-itemsOut.getItemTotal());
            outBillDao.updateOutBill(outBill);
            int i = itemsOutDao.delItemsOutByID(itemsOutID);
            //若出库单合计减去当前商品合计<=0,则删除该出库单
            if ((outBill.getAllTotal()-itemsOut.getItemTotal())<=0){
                return outBillDao.delOutBillByOutBillID(outBill.getOutBillID());
            }
            return i;
        }

    }

    @Override
    public ItemsOut findItemsOutByID(int itemsOutID) {
        return itemsOutDao.findItemsOutByID(itemsOutID);
    }

    @Override
    public List<ItemsOut> findItemsOutByOutBillID(int outBillID) {
        return itemsOutDao.findItemsOutByOutBillID(outBillID);
    }
    @Override
    public void delItemByOutBillID(int outBill) {
        //outBill，然后操作汇总表，逐个清零
        List<OutBillPresent> itemsOutData = itemsOutDao.findItemsOutData(outBill);
        String time = outBillDao.findTimeByID(outBill);
        String subTime = time.substring(0, 7);

        for (OutBillPresent itemsOutDatum : itemsOutData) {
            try {
                Summary summary = summaryDao.findSummaryByGoodsIDAndTime(itemsOutDatum.getGoodsID(), subTime);
                if(summary!=null){
                    summary.setOutAmount(summary.getOutAmount()- itemsOutDatum.getItemNum());
                    summary.setOutTotal(summary.getOutTotal()- itemsOutDatum.getItemTotal());
                    summary.setOutPrice(new BigDecimal(summary.getThisTotal()/summary.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    summary.setThisAmount(summary.getPreAmount()+summary.getInAmount()-summary.getOutAmount());
                    summary.setThisTotal(summary.getPreTotal()+summary.getInTotal()-summary.getOutTotal());
                    if(summary.getThisAmount()==0){
                        summary.setThisPrice(0.0);
                    }else{
                        summary.setThisPrice(new BigDecimal(summary.getThisTotal()/summary.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                    summaryDao.updateSummary(summary);
                    //插入本月数据后，同步后面时间的数据
                    List<Summary> timeAfter = summaryDao.findSummaryByGoodsIDAndTimeAfter(itemsOutDatum.getGoodsID(), subTime);
                    for (Summary s : timeAfter) {
                        //判断是否是本月的数据，然后修改后面时间的数据
                        if(!s.getTime().equals(subTime)){
                            //将上月的本月结存放入到当前月的上月结存
                            String nowTime = s.getTime()+"-1";
                            Summary frontSummary=summaryDao.findNearestSummaryByIdAndTime(s.getGoodsID(),s.getTime());
                            //将上月的本月结存放入到当前月的上月结存
                            s.setPreAmount(frontSummary.getThisAmount());
                            s.setPrePrice(frontSummary.getThisPrice());
                            s.setPreTotal(frontSummary.getThisTotal());
                            s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                            s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                            if(s.getThisAmount()==0){
                                s.setThisPrice(0.0);
                            }else{
                                s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            }
                            summaryDao.updateSummary(s);
                        }
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                itemsOutDao.delItemByOutBillID(outBill);
            }

        }
    }

    @LogAnno(operateType = "更新出库物品")
    @Override
    public Integer updateItemsOut(ItemsOut itemsOutNew) {
        //查找原来的数据
        ItemsOut itemsOutOld = itemsOutDao.findItemsOutByID(itemsOutNew.getItemsOutID());

        //如果出库数量增加
        if (itemsOutNew.getItemNum()>itemsOutOld.getItemNum()){
            updateSummary(itemsOutNew,itemsOutOld,1);
        }

        //如果出库数量减少
        if (itemsOutNew.getItemNum()<itemsOutOld.getItemNum()){
            updateSummary(itemsOutNew,itemsOutOld,0);
        }
        return itemsOutDao.updateItemsOut(itemsOutNew);
    }


    public int updateSummary(ItemsOut itemsOutNew,ItemsOut itemsOutOld,int flag){
        int i = 0;

        //根据出库物品的outBillID，查找到出库单记录
        OutBill outBill = outBillDao.findOutBillByID(itemsOutNew.getOutBillID());
        //获取出库单的出库时间
        String outTime = outBill.getOutTime();

        //截取出库时间的前7位，如：2019-12-11 ---> 2019-12
        outTime = outTime.substring(0,7);

        //根据出库物品的id和截取后的出库时间，查询汇总表中，该时间及该时间后的同一个出库物品id的汇总数据
        List<Summary> summaryList = summaryDao.findSummaryByGoodsIDAndTimeAfter(itemsOutNew.getGoodsID(), outTime);
        for (Summary s :
                summaryList) {

            if (flag==0){
                if (s.getTime().equals(outTime)){
                //减少本月出库数量，合计
                    s.setOutAmount(s.getOutAmount() - itemsOutOld.getItemNum()+itemsOutNew.getItemNum());
                    s.setOutTotal(s.getOutTotal()-itemsOutOld.getItemTotal()+ itemsOutNew.getItemTotal());
                    s.setOutPrice(new BigDecimal(s.getOutTotal()/s.getOutAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                    //增加本月数量，合计
                    s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                    s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                    s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                }

                //修改该出库时间 后面 的数据
                if (!s.getTime().equals(outTime)){
                    String nowTime = s.getTime()+"-1";
                    Summary frontSummary=summaryDao.findNearestSummaryByIdAndTime(s.getGoodsID(),s.getTime());
                    //将上月的本月结存放入到当前月的上月结存
                    if(frontSummary!=null){
                        s.setPreAmount(frontSummary.getThisAmount());
                        s.setPrePrice(frontSummary.getThisPrice());
                        s.setPreTotal(frontSummary.getThisTotal());
                        s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                        s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                        if(s.getThisAmount()==0){
                            s.setThisPrice(0.0);
                        }else{
                            s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        }
                    }else{
                        s.setPreAmount(0);
                        s.setPrePrice(0.0);
                        s.setPreTotal(0.0);
                        s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                        s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                        if(s.getThisAmount()==0){
                            s.setThisPrice(0.0);
                        }else{
                            s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        }
                    }

                    if(s.getPreAmount()==0&&s.getInAmount()==0&&s.getOutAmount()==0&&s.getThisAmount()==0){
                        summaryDao.delSummaryByid(s.getSummaryID());
                    }else {
                        summaryDao.updateSummary(s);
                    }
                }
            }
            if (flag==1){
                if(s.getTime().equals(outTime)) {
                    //增加出库数量，合计
                    s.setOutAmount(s.getOutAmount() - itemsOutOld.getItemNum()+itemsOutNew.getItemNum());
                    s.setOutTotal(s.getOutTotal()-itemsOutOld.getItemTotal()+ itemsOutNew.getItemTotal());
                    s.setOutPrice(new BigDecimal(s.getOutTotal()/s.getOutAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                    //减少本月数量，合计
                    s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                    s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                    s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                }
                //修改该出库时间后面的数据
                if (!s.getTime().equals(outTime)){
                    String nowTime = s.getTime()+"-1";
                    Summary frontSummary=summaryDao.findNearestSummaryByIdAndTime(s.getGoodsID(),s.getTime());
                    //将上月的本月结存放入到当前月的上月结存
                    if(frontSummary!=null){
                        s.setPreAmount(frontSummary.getThisAmount());
                        s.setPrePrice(frontSummary.getThisPrice());
                        s.setPreTotal(frontSummary.getThisTotal());
                        s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                        s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                        if(s.getThisAmount()==0){
                            s.setThisPrice(0.0);
                        }else{
                            s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        }
                    }else{
                        s.setPreAmount(0);
                        s.setPrePrice(0.0);
                        s.setPreTotal(0.0);
                        s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                        s.setThisTotal(s.getPreTotal()+s.getInTotal()-s.getOutTotal());
                        if(s.getThisAmount()==0){
                            s.setThisPrice(0.0);
                        }else{
                            s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        }
                    }

                    if(s.getPreAmount()==0&&s.getInAmount()==0&&s.getOutAmount()==0&&s.getThisAmount()==0){
                        summaryDao.delSummaryByid(s.getSummaryID());
                    }else {
                        summaryDao.updateSummary(s);
                    }
                }
            }
            //修改完数据后，更新该条汇总记录
            i = summaryDao.updateSummary(s);
            if (i<1)
                return i;
        }
        return i;
    }
}
