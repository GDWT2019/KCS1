package com.kcs.rest.pojo;

public class Category {

    private Integer CategoryID;
    private String CategoryName;

    @Override
    public String toString() {
        return "Category{" +
                "CategoryID=" + CategoryID +
                ", CategoryName='" + CategoryName + '\'' +
                '}';
    }

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
