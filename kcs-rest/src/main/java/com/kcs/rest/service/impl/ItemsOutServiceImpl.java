package com.kcs.rest.service.impl;

import com.kcs.rest.dao.ItemsOutDao;
import com.kcs.rest.dao.OutBillDao;
import com.kcs.rest.dao.SummaryDao;
import com.kcs.rest.pojo.ItemsOut;
import com.kcs.rest.pojo.OutBill;
import com.kcs.rest.pojo.Summary;
import com.kcs.rest.service.ItemsOutService;
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

    @Override
    public int insertItemsOut(ItemsOut itemsOut) {
        return itemsOutDao.insertItemsOut(itemsOut);
    }

    /**
     * 删除出库物品时，汇总表的出库记录减少对应的数量，合计；增加本月的数量，合计；上月的数量，合计
     * @param itemsOutID
     * @return
     */
    @Override
    public Integer delItemsOutByID(int itemsOutID) {

        //根据id，找到出库物品记录
        ItemsOut itemsOut = itemsOutDao.findItemsOutByID(itemsOutID);

        //根据出库物品的outBillID，查找到出库单记录
        OutBill outBill = outBillDao.findOutBillByID(itemsOut.getOutBillID());

        //获取出库单的出库时间
        String outTime = outBill.getOutTime();

        //截取出库时间的前7位，如：2019-12-11 ---> 2019-12
        outTime = outTime.substring(0,7);

        //根据出库物品的id和截取后的出库时间，查询汇总表中，该时间及该时间后的同一个出库物品id的汇总数据
        List<Summary> summaryList = summaryDao.findSummaryByGoodsIDAndTimeAfter(itemsOut.getGoodsID(), outTime);
        for (Summary s :
                summaryList) {

            //减少出库数量，合计
            s.setOutAmount(s.getOutAmount()-itemsOut.getItemNum());
            s.setOutTotal(s.getOutTotal()-itemsOut.getItemTotal());

            //增加本月数量，合计
            s.setThisAmount(s.getThisAmount()+itemsOut.getItemNum());
            s.setThisTotal(s.getThisTotal()+itemsOut.getItemTotal());

            //修改该出库时间后的数据
            if (!s.getTime().equals(outTime)){
                //修改上月的数量，合计
                s.setPreAmount(s.getThisAmount()+itemsOut.getItemNum());
                s.setPreTotal(s.getThisTotal()+itemsOut.getItemTotal());
            }

            //修改完数据后，更新该条汇总记录
            summaryDao.updateSummary(s);
        }

        return itemsOutDao.delItemsOutByID(itemsOutID);
    }

    @Override
    public ItemsOut findItemsOutByID(int itemsOutID) {
        return null;
    }
}
