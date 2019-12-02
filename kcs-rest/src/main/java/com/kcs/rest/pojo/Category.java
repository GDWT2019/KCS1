package com.kcs.rest.pojo;

public class Category {
    private Integer CategoryID;     //类别id

    private String CategoryName;     //类别名称

    public Integer getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Integer categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}