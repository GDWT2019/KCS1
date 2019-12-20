package com.kcs.portal.service;

import com.kcs.rest.pojo.GoodsAndCategoryAndItemsIn;
import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.ItemIn;
import com.kcs.rest.pojo.ItemsShow;

import java.util.List;

public interface ItemInService {
    void insertNewItem(ItemIn itemIn);

    List<ItemsShow> findItemsInData(String inBillID);

    void delItem(String itemsInID);

    void UpdateCheckStatus(InBill inBill);

    List<InBill> valueIDandTime(String inBillID);

    List<GoodsAndCategoryAndItemsIn> getItemsInList(String inBillID);

    void delItemByInBillID(String inBillID);
}
