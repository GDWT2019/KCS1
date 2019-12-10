package com.kcs.rest.service;

import com.kcs.rest.pojo.ItemIn;
import com.kcs.rest.pojo.ItemsShow;

import java.util.List;

public interface ItemInService {
    void insertNewItem(ItemIn itemIn);

    List<ItemsShow> findItemsInData(int inBillID);

    void delItem(int itemsInID);
}
