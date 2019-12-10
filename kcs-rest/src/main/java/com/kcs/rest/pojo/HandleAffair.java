package com.kcs.rest.pojo;

import java.util.List;

public class HandleAffair {

    private List<ItemIn> itemInList;

    @Override
    public String toString() {
        return "HandleAffair{" +
                "itemInList=" + itemInList +
                '}';
    }

    public List<ItemIn> getItemInList() {
        return itemInList;
    }

    public void setItemInList(List<ItemIn> itemInList) {
        this.itemInList = itemInList;
    }
}
