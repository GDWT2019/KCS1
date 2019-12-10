package com.kcs.portal.service;

import com.kcs.rest.pojo.ItemIn;
import com.kcs.rest.pojo.ItemsShow;

import java.util.List;

public interface ItemInService {
    void insertNewItem(ItemIn itemIn);

    List<ItemsShow> findItemsInData(String inBillID);

    void delItem(String itemsInID);
}
