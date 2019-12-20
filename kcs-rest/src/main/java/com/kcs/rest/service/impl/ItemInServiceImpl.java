package com.kcs.rest.service.impl;

import com.kcs.rest.dao.ItemInDao;
import com.kcs.rest.pojo.GoodsAndCategoryAndItemsIn;
import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.ItemIn;
import com.kcs.rest.pojo.ItemsShow;
import com.kcs.rest.service.ItemInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("itemInService")
public class ItemInServiceImpl implements ItemInService {

    @Autowired
    private ItemInDao itemInDao;

    @Override
    public void insertNewItem(ItemIn itemIn) {
        itemInDao.insertNewItem(itemIn);
    }

    @Override
    public List<ItemsShow> findItemsInData(int inBillID) {
        return itemInDao.findItemsInData(inBillID);
    }

    @Override
    public void delItem(int itemsInID) {
        itemInDao.delItem(itemsInID);
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
        itemInDao.delItemByInBillID(inBillID);
    }
}
