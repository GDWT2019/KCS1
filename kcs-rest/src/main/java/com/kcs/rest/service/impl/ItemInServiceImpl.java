package com.kcs.rest.service.impl;

import com.kcs.rest.dao.InBillDao;
import com.kcs.rest.dao.ItemInDao;
import com.kcs.rest.dao.SummaryDao;
import com.kcs.rest.pojo.*;
import com.kcs.rest.service.ItemInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service("itemInService")
public class ItemInServiceImpl implements ItemInService {

    @Autowired
    private ItemInDao itemInDao;
    @Autowired
    private SummaryDao summaryDao;
    @Autowired
    private InBillDao inBillDao;

    @Override
    public void insertNewItem(ItemIn itemIn) {
        Integer goodsID = itemIn.getGoodsID();
        Integer inBillID = itemIn.getInBillID();
        itemInDao.insertNewItem(itemIn);

        String time = inBillDao.findTimeByID(inBillID);
        String subTime = time.substring(0, 7);
        Summary summary = summaryDao.findSummaryByGoodsIDAndTime(goodsID, subTime);
        //如果有数据就直接更新
        if(summary!=null){
            summary.setInAmount(summary.getInAmount()+itemIn.getItemNum());
            summary.setInTotal(summary.getInTotal()+itemIn.getItemTotal());
            summary.setInPrice(new BigDecimal(summary.getInTotal()/summary.getInAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            summary.setThisAmount(summary.getPreAmount()+summary.getInAmount()-summary.getOutAmount());
            summary.setThisTotal(summary.getPreTotal()+summary.getInTotal()-summary.getOutTotal());
            summary.setThisPrice(new BigDecimal(summary.getThisTotal()/summary.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
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
        else
        {
            Summary BeforeSummary=summaryDao.findNearestSummaryByIdAndTime(goodsID,time);
            //如果存在上个月的数据，将上月的本月库存存在当前月的上月库存
            if(BeforeSummary!=null){
                Summary summary1=new Summary();
            summary1.setPreAmount(BeforeSummary.getThisAmount());
            summary1.setPrePrice(BeforeSummary.getThisPrice());
            summary1.setPreTotal(BeforeSummary.getThisTotal());
            summary1.setGoodsID(itemIn.getGoodsID());
            summary1.setInAmount(itemIn.getItemNum());
            summary1.setInTotal(itemIn.getItemTotal());
            summary1.setInPrice(new BigDecimal(summary1.getInTotal()/summary1.getInAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            summary1.setOutAmount(0);
            summary1.setOutPrice(0.0);
            summary1.setOutTotal(0.0);
            summary1.setThisAmount(summary1.getPreAmount()+summary1.getInAmount()-summary1.getOutAmount());
            summary1.setThisTotal(summary1.getPreTotal()+summary1.getInTotal()-summary1.getOutTotal());
            summary1.setThisPrice(new BigDecimal(summary1.getThisTotal()/summary1.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
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
                Summary summary2=new Summary();
                summary2.setPreAmount(0);
                summary2.setPrePrice(0.0);
                summary2.setPreTotal(0.0);
                summary2.setGoodsID(itemIn.getGoodsID());
                summary2.setInAmount(itemIn.getItemNum());
                summary2.setInTotal(itemIn.getItemTotal());
                summary2.setInPrice(new BigDecimal(summary2.getInTotal()/summary2.getInAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                summary2.setOutAmount(0);
                summary2.setOutPrice(0.0);
                summary2.setOutTotal(0.0);
                summary2.setThisAmount(summary2.getPreAmount() + summary2.getInAmount()- summary2.getOutAmount());
                summary2.setThisTotal(summary2.getPreTotal()+summary2.getInTotal()-summary2.getOutTotal());
                summary2.setThisPrice(new BigDecimal(summary2.getThisTotal()/summary2.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
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

    @Override
    public List<ItemsShow> findItemsInData(int inBillID) {
        return itemInDao.findItemsInData(inBillID);
    }

    @Override
    public void delItem(int itemsInID) {
        ItemIn itemIn =itemInDao.finditemsByItemsID(itemsInID);
        Integer inBillID = itemIn.getInBillID();
        //通过inBillID查询属于改单号的物品，然后操作汇总表，逐个清零
//        List<ItemsShow> itemsInData = itemInDao.findItemsInData(inBillID);
        String time = inBillDao.findTimeByID(inBillID);
        String subTime = time.substring(0, 7);

            try {
                Summary summary = summaryDao.findSummaryByGoodsIDAndTime(itemIn.getGoodsID(), subTime);
                if(summary!=null){
                    summary.setInAmount(summary.getInAmount()-itemIn.getItemNum());
                    summary.setInTotal(summary.getInTotal()-itemIn.getItemTotal());
                    summary.setInPrice(new BigDecimal(summary.getThisTotal()/summary.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
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
                    List<Summary> timeAfter = summaryDao.findSummaryByGoodsIDAndTimeAfter(itemIn.getGoodsID(), subTime);
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
                itemInDao.delItem(itemsInID);
            }


    }

    @Override
    public ItemIn findItemsByGoodsID(int GoodsID) {
        return itemInDao.findItemsByGoodsID(GoodsID);
    }

    @Override
    public void UpdateCheckStatus(InBill inBill) {
        itemInDao.UpdateCheckStatus(inBill);
    }

    @Override
    public List<InBill> valueIDandTime(int billID) {
        return itemInDao.valueIDandTime(billID);
    }

    @Override
    public List<GoodsAndCategoryAndItemsIn> getItemsInList(int billID) {
        return itemInDao.getItemsInList(billID);
    }

    @Override
    public void delItemByInBillID(int inBillID) {
        //通过inBillID查询属于该单号的物品，然后操作汇总表，逐个清零
        List<ItemsShow> itemsInData = itemInDao.findItemsInData(inBillID);
        String time = inBillDao.findTimeByID(inBillID);
        String subTime = time.substring(0, 7);

        for (ItemsShow itemsInDatum : itemsInData) {
            try {
                Summary summary = summaryDao.findSummaryByGoodsIDAndTime(itemsInDatum.getGoodsID(), subTime);
                if(summary!=null){
                    summary.setInAmount(summary.getInAmount()-itemsInDatum.getItemNum());
                    summary.setInTotal(summary.getInTotal()-itemsInDatum.getItemTotal());
                    summary.setInPrice(new BigDecimal(summary.getThisTotal()/summary.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    summary.setThisAmount(summary.getPreAmount()+summary.getInAmount()-summary.getOutAmount());
                    summary.setThisTotal(summary.getPreTotal()+summary.getInTotal()-summary.getOutTotal());
                    if(summary.getThisAmount()==0){
                        summary.setThisPrice(0.0);
                    }else{
                    summary.setThisPrice(new BigDecimal(summary.getThisTotal()/summary.getThisAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                    summaryDao.updateSummary(summary);
                    //插入本月数据后，同步后面时间的数据
                    List<Summary> timeAfter = summaryDao.findSummaryByGoodsIDAndTimeAfter(itemsInDatum.getGoodsID(), subTime);
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
                itemInDao.delItemByInBillID(inBillID);
            }

        }
    }
}
