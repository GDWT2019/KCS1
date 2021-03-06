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

        //用于更新的summary
        Summary summary = null;

        //更新出库物品仓库位置
        ItemIn itemIn = itemInDao.findItemsByGoodsID(itemsOut.getGoodsID());
        if (itemIn.getStorePosition()!=null){
            itemsOut.setStorePosition(itemIn.getStorePosition());
        }

        //获取该出库物品对应的出库单的月份
        OutBill outBillByID = outBillDao.findOutBillByID(itemsOut.getOutBillID());
        String month = outBillByID.getOutTime().substring(0,7);
        int number = itemsOut.getItemNum();
        List<Summary> summaryByGoodsIDAndTimeAfter = summaryDao.findSummaryByGoodsIDAndTimeAfter(itemsOut.getGoodsID(), month);
        for (Summary s :
                summaryByGoodsIDAndTimeAfter) {
            if(s.getThisAmount()<number){
                return 2;
            }
        }
        for (Summary s :
                summaryByGoodsIDAndTimeAfter) {
            if(s.getTime().equals(month)){
                //增加本月出库数
                s.setOutAmount(s.getOutAmount()+number);
                s.setOutTotal(new BigDecimal(s.getOutTotal()+itemsOut.getItemTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                if(s.getOutAmount()!=0)
                    s.setOutPrice(new BigDecimal(s.getOutTotal()/s.getOutAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                else
                    s.setOutPrice(0d);

                //减少本月结存数
                s.setThisAmount(s.getThisAmount()-number);
                s.setThisTotal(new BigDecimal(s.getThisTotal()-itemsOut.getItemTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                if(s.getThisAmount()!=0)
                    s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                else
                    s.setThisPrice(0d);
            }else {
                //减少上月结存数
                s.setPreAmount(s.getPreAmount()-number);
                s.setPreTotal(new BigDecimal(s.getPreTotal()-itemsOut.getItemTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                if(s.getPreAmount()!=0)
                    s.setPrePrice(new BigDecimal(s.getPreTotal()/s.getPreAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                else
                    s.setPrePrice(0d);
                //减少本月结存数
                s.setThisAmount(s.getThisAmount()-number);
                s.setThisTotal(new BigDecimal(s.getThisTotal()-itemsOut.getItemTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                if(s.getThisAmount()!=0)
                    s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                else
                    s.setThisPrice(0d);
            }
            summaryDao.updateSummary(s);
        }
        int i = itemsOutDao.insertItemsOut(itemsOut);
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


        //根据出库物品的outBillID，查找到出库单记录
        OutBill outBill = outBillDao.findOutBillByID(itemsOut.getOutBillID());

        //修改出库单信息，并更新
        outBill.setAllTotal(new BigDecimal(outBill.getAllTotal()-itemsOut.getItemTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        outBillDao.updateOutBill(outBill);
        String outTime = outBill.getOutTime().substring(0,7);
        List<Summary> summaryByGoodsIDAndTimeAfter = summaryDao.findSummaryByGoodsIDAndTimeAfter(itemsOut.getGoodsID(), outTime);

        for (Summary s :
                summaryByGoodsIDAndTimeAfter) {
            //修改删除的对应月
            if (s.getTime().equals(outTime)) {
                s.setOutAmount(0);
                s.setOutTotal(0d);

                s.setThisAmount(s.getPreAmount()+s.getInAmount());
                if(s.getThisAmount()==0){
                    s.setThisTotal(0.0);
                    s.setThisPrice(0.0);
                }else{
                    s.setThisTotal(new BigDecimal(s.getPreTotal()+s.getInTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }
            if (!s.getTime().equals(outTime)) {
                s.setPreAmount(s.getPreAmount()+itemsOut.getItemNum());
                if(s.getPreAmount()==0){
                    s.setPreTotal(0.0);
                    s.setPrePrice(0.0);
                }else{
                    s.setPreTotal(s.getPreTotal()+itemsOut.getItemTotal());
                    s.setPrePrice(new BigDecimal(s.getPreTotal()/s.getPreAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
                s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());

                if(s.getThisAmount()==0){
                    s.setThisTotal(0.0);
                    s.setThisPrice(0.0);
                }else{
                    s.setThisTotal(new BigDecimal(s.getPreTotal()+s.getInTotal()-s.getOutTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }

            summaryDao.updateSummary(s);
        }
        int i = itemsOutDao.delItemsOutByID(itemsOutID);
         //若出库单合计减去当前商品合计<=0,则删除该出库单
        if ((outBill.getAllTotal())<=0){
            return outBillDao.delOutBillByOutBillID(outBill.getOutBillID());
        }
         return i;
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
                    summary.setOutTotal(new BigDecimal(summary.getOutTotal()- itemsOutDatum.getItemTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    summary.setOutPrice(new BigDecimal(summary.getOutTotal()/summary.getOutAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    summary.setThisAmount(summary.getPreAmount()+summary.getInAmount()-summary.getOutAmount());
                    if(summary.getThisAmount()==0){
                        summary.setThisTotal(0.0);
                        summary.setThisPrice(0.0);
                    }else{
                        summary.setThisTotal(new BigDecimal(summary.getPreTotal()+summary.getInTotal()-summary.getOutTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
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
                            if(s.getThisAmount()==0){
                                s.setThisTotal(0.0);
                                s.setThisPrice(0.0);
                            }else{
                                s.setThisTotal(new BigDecimal(s.getPreTotal()+s.getInTotal()-s.getOutTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
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
                    s.setOutAmount(s.getOutAmount()-(itemsOutOld.getItemNum()-itemsOutNew.getItemNum()));
                    s.setOutTotal(new BigDecimal(s.getOutTotal()-(itemsOutOld.getItemTotal()-itemsOutNew.getItemTotal())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if (s.getOutAmount() != 0)
                    s.setOutPrice(new BigDecimal(s.getOutTotal()/s.getOutAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    //增加本月数量，合计
                    s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                    s.setThisTotal(new BigDecimal(s.getPreTotal()+s.getInTotal()-s.getOutTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if (s.getThisAmount() != 0)
                    s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }

                //修改该出库时间 后面 的数据
                if (!s.getTime().equals(outTime)){
                    //增加上月的数量，合计
                    s.setPreAmount(s.getPreAmount()+(itemsOutOld.getItemNum()-itemsOutNew.getItemNum()));
                    s.setPreTotal(new BigDecimal(s.getPreTotal()+itemsOutOld.getItemTotal()-itemsOutNew.getItemTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if (s.getPreAmount() != 0)
                    s.setPrePrice(new BigDecimal(s.getPreTotal()/s.getPreAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    //增加本月数量，合计
                    s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                    s.setThisTotal(new BigDecimal(s.getPreTotal()+s.getInTotal()-s.getOutTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if (s.getThisAmount() != 0)
                    s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                }
            }
            if (flag==1){
                if(s.getTime().equals(outTime)) {
                    //增加出库数量，合计
                    s.setOutAmount(s.getOutAmount() + (itemsOutNew.getItemNum()-itemsOutOld.getItemNum()));
                    s.setOutTotal(new BigDecimal(s.getOutTotal() + (itemsOutNew.getItemTotal()-itemsOutOld.getItemTotal())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if (s.getOutAmount() != 0)
                    s.setOutPrice(new BigDecimal(s.getOutTotal()/s.getOutAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                    //减少本月数量，合计
                    s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                    s.setThisTotal(new BigDecimal(s.getPreTotal()+s.getInTotal()-s.getOutTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if (s.getThisAmount() != 0)
                        s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
                //修改该出库时间后面的数据
                if (!s.getTime().equals(outTime)){
                    //减少上月的数量，合计
                    s.setPreAmount(s.getPreAmount()-(itemsOutNew.getItemNum()-itemsOutOld.getItemNum()));
                    s.setPreTotal(new BigDecimal(s.getPreTotal()-(itemsOutNew.getItemTotal()-itemsOutOld.getItemTotal())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if (s.getPreAmount() != 0)
                    s.setPrePrice(new BigDecimal(s.getPreTotal()/s.getPreAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    //减少本月数量，合计
                    s.setThisAmount(s.getPreAmount()+s.getInAmount()-s.getOutAmount());
                    s.setThisTotal(new BigDecimal(s.getPreTotal()+s.getInTotal()-s.getOutTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    if (s.getThisAmount() != 0)
                    s.setThisPrice(new BigDecimal(s.getThisTotal()/s.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
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
