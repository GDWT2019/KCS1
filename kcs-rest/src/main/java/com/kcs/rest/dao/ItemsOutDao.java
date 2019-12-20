package com.kcs.rest.dao;

import com.kcs.rest.pojo.ItemsOut;

import java.util.List;

public interface ItemsOutDao {

    //插入itemsOut
    int insertItemsOut(ItemsOut itemsOut);

    //根据id，删除出库物品数据
    Integer delItemsOutByID(int itemsOutID);

    //根据id，查询记录
    ItemsOut findItemsOutByID(int itemsOutID);

    //根据出库id OutBillID，查找出库物品
    List<ItemsOut> findItemsOutByOutBillID(int outBillID);

    //根据id更新入库物品信息，为空则不更新该字段
    Integer updateItemsOut(ItemsOut itemsOut);
}
