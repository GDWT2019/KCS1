package com.kcs.rest.dao;

import com.kcs.rest.pojo.*;
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

    int findInBillByItemsID(int itemsInID);

    ItemIn finditemsByItemsID(int itemsInID);

    Float findAllTotal(Integer inBillID);

    void delItemByInBillIDandGoodsID(@Param("inBillID") int inBillID, @Param("goodsID")int goodsID);

    ItemIn findItemsInByItemsID(int itemsInID);

    List<ItemIn> findItemsIdByInBillID(int inBillID);

    Integer findSumItemNumBygoodsIdAndInBillID(@Param("goodsID")int gid,@Param("inBillID") String inBillID);
}
