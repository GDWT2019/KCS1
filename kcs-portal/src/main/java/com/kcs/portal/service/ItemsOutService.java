package com.kcs.portal.service;

import com.kcs.rest.pojo.ItemsOut;

import java.util.List;

public interface ItemsOutService {

    //插入出库物品数据
    Integer insertItemsOut(ItemsOut itemsOut);

    //根据id，删除出库物品数据
    Integer delItemsOutByID(int itemsOutID);
}
