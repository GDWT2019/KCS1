package com.kcs.rest.service.impl;

import com.kcs.rest.dao.ItemInDao;
import com.kcs.rest.dao.ItemsOutDao;
import com.kcs.rest.dao.OutBillDao;
import com.kcs.rest.dao.SummaryDao;
import com.kcs.rest.pojo.ItemIn;
import com.kcs.rest.pojo.ItemsOut;
import com.kcs.rest.pojo.OutBill;
import com.kcs.rest.pojo.Summary;
import com.kcs.rest.service.ItemsOutService;
import com.kcs.rest.utils.LogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @LogAnno(operateType = "新增出库物品")
    @Override
    public int insertItemsOut(ItemsOut itemsOut) {
        //更新出库物品仓库位置
        ItemIn itemIn = itemInDao.findItemsByGoodsID(itemsOut.getGoodsID());
        if (itemIn.getStorePosition()!=null){
            itemsOut.setStorePosition(itemIn.getStorePosition());
        }
        //插入出库物品信息
        int i = itemsOutDao.insertItemsOut(itemsOut);
        if (i>0){
            //获取对应物品id的最新的汇总记录
            Summary summary = summaryDao.findSummaryInTheLastGoodsDataByGoodsID(itemsOut.getGoodsID());

            //判断该记录出库信息
            if (summary.getOutAmount()>=0||summary.getOutAmount()!=null){
                //出库物品插入后，更新汇总表出库信息
                summary.setOutAmount(summary.getOutAmount()+itemsOut.getItemNum());
                summary.setOutTotal(summary.getOutTotal()+itemsOut.getItemTotal());
            }
            else{
                summary.setOutAmount(itemsOut.getItemNum());
                summary.setOutTotal(itemsOut.getItemTotal());
            }
            summary.setOutPrice(itemsOut.getItemPrice());
            summary.setThisAmount(summary.getThisAmount()-itemsOut.getItemNum());
            summary.setThisTotal(summary.getThisTotal()-itemsOut.getItemTotal());
            summaryDao.updateSummary(summary);
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


        //根据出库物品的outBillID，查找到出库单记录
        OutBill outBill = outBillDao.findOutBillByID(itemsOut.getOutBillID());

        //修改出库单信息，并更新
        outBill.setAllTotal(outBill.getAllTotal()-itemsOut.getItemTotal());
        outBillDao.updateOutBill(outBill);
        String outTime = outBill.getOutTime().substring(0,7);
        List<Summary> summaryByGoodsIDAndTimeAfter = summaryDao.findSummaryByGoodsIDAndTimeAfter(itemsOut.getGoodsID(), outTime);

        for (Summary s :
                summaryByGoodsIDAndTimeAfter) {
            //修改删除的对应月
            if (s.getTime().equals(outTime)) {
                s.setOutAmount(s.getOutAmount()-itemsOut.getItemNum());
                s.setOutTotal(s.getOutTotal()-itemsOut.getItemTotal());
                s.setThisAmount(s.getThisAmount()+itemsOut.getItemNum());
                s.setThisTotal(s.getThisTotal()+itemsOut.getItemTotal());
            }
            if (!s.getTime().equals(outTime)) {
                s.setPreAmount(s.getPreAmount()+itemsOut.getItemNum());
                s.setPreTotal(s.getPreTotal()+itemsOut.getItemTotal());
                s.setThisAmount(s.getThisAmount()+itemsOut.getItemNum());
                s.setThisTotal(s.getThisTotal()+itemsOut.getItemTotal());
            }

            summaryDao.updateSummary(s);
        }
        int i = itemsOutDao.delItemsOutByID(itemsOutID);
         //若出库单合计减去当前商品合计<=0,则删除该出库单
        if ((outBill.getAllTotal()-itemsOut.getItemTotal())<=0){
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
                    s.setOutTotal(s.getOutTotal()-(itemsOutOld.getItemTotal()-itemsOutNew.getItemTotal()));
                    //增加本月数量，合计
                    s.setThisAmount(s.getThisAmount()+(itemsOutOld.getItemNum())-itemsOutNew.getItemNum());
                    s.setThisTotal(s.getThisTotal()+(itemsOutOld.getItemTotal()-itemsOutNew.getItemTotal()));
                }

                //修改该出库时间 后面 的数据
                if (!s.getTime().equals(outTime)){
                    //增加上月的数量，合计
                    s.setPreAmount(s.getThisAmount()+(itemsOutOld.getItemNum()-itemsOutNew.getItemNum()));
                    s.setPreTotal(s.getThisTotal()+(itemsOutOld.getItemTotal()-itemsOutNew.getItemTotal()));

                    //增加本月数量，合计
                    s.setThisAmount(s.getThisAmount()+(itemsOutOld.getItemNum()-itemsOutNew.getItemNum()));
                    s.setThisTotal(s.getThisTotal()+(itemsOutOld.getItemTotal()-itemsOutNew.getItemTotal()));
                }
            }
            if (flag==1){
                if(s.getTime().equals(outTime)) {
                    //增加出库数量，合计
                    s.setOutAmount(s.getOutAmount() + (itemsOutNew.getItemNum()-itemsOutOld.getItemNum()));
                    s.setOutTotal(s.getOutTotal() + (itemsOutNew.getItemTotal()-itemsOutOld.getItemTotal()));

                    //减少本月数量，合计
                    s.setThisAmount(s.getThisAmount() - (itemsOutNew.getItemNum()-itemsOutOld.getItemNum()));
                    s.setThisTotal(s.getThisTotal() - (itemsOutNew.getItemTotal()-itemsOutOld.getItemTotal()));
                }
                //修改该出库时间后面的数据
                if (!s.getTime().equals(outTime)){
                    //减少上月的数量，合计
                    s.setPreAmount(s.getThisAmount()- (itemsOutNew.getItemNum()-itemsOutOld.getItemNum()));
                    s.setPreTotal(s.getThisTotal()-(itemsOutNew.getItemTotal()-itemsOutOld.getItemTotal()));
                    //增加本月数量，合计
                    s.setThisAmount(s.getThisAmount()- (itemsOutNew.getItemNum()-itemsOutOld.getItemNum()));
                    s.setThisTotal(s.getThisTotal()-(itemsOutNew.getItemTotal()-itemsOutOld.getItemTotal()));
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