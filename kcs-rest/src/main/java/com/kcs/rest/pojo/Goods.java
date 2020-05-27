package com.kcs.rest.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.kcs.rest.pojo.Category;

public class Goods implements Serializable {
    private Integer GoodsID;        //物品id

    private Integer CategoryID;     //类别id

    private String ItemsName;       //物品名称

    private String ItemsType;       //物品规格

    private String ItemsUnit;       //物品单位

    private String Image;           //物品图片

    private List<Category> Categories =new ArrayList<>();//连接表


    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public List<Category> getCategories() {
        return Categories;
    }

    public void setCategories(List<Category> categories) {
        Categories = categories;
    }

    public Integer getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(Integer goodsID) {
        GoodsID = goodsID;
    }

    public Integer getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.CategoryID = categoryID;
    }

    public String getItemsName() {
        return ItemsName;
    }

    public void setItemsName(String itemsName) {
        ItemsName = itemsName;
    }

    public String getItemsType() {
        return ItemsType;
    }

    public void setItemsType(String itemsType) {
        ItemsType = itemsType;
    }

    public String getItemsUnit() {
        return ItemsUnit;
    }

    public void setItemsUnit(String itemsUnit) {
        ItemsUnit = itemsUnit;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "GoodsID=" + GoodsID +
                ", CategoryID=" + CategoryID +
                ", ItemsName='" + ItemsName + '\'' +
                ", ItemsType='" + ItemsType + '\'' +
                ", ItemsUnit='" + ItemsUnit + '\'' +
                '}';
    }
}