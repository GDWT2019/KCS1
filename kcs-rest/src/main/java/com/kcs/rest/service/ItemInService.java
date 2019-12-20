package com.kcs.rest.service;

import com.kcs.rest.pojo.GoodsAndCategoryAndItemsIn;
import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.ItemIn;
import com.kcs.rest.pojo.ItemsShow;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemInService {
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    void insertNewItem(ItemIn itemIn);

    List<ItemsShow> findItemsInData(int inBillID);

    void delItem(int itemsInID);

    ItemIn findItemsByGoodsID(int GoodsID);

    void UpdateCheckStatus(InBill inBill);

    List<InBill> valueIDandTime(int billID);

    List<GoodsAndCategoryAndItemsIn> getItemsInList(int billID);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    void delItemByInBillID(int inBillID);
}
