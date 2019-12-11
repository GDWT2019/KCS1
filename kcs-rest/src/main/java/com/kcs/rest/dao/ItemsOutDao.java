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
}
