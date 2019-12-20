package com.kcs.rest.dao;

import com.kcs.rest.pojo.GoodsAndCategoryAndItemsIn;
import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.ItemIn;
import com.kcs.rest.pojo.ItemsShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemInDao {
    void insertNewItem(ItemIn itemIn);

    List<ItemsShow> findItemsInData(int inBillID);

    void delItem(int itemsInID);

    ItemIn findItemsByGoodsID(@Param("GoodsID")int GoodsID);

    void UpdateCheckStatus(InBill inBill);

    List<InBill> valueIDandTime(int billID);

    List<GoodsAndCategoryAndItemsIn> getItemsInList(int billID);

    void delItemByInBillID(int inBillID);
}
