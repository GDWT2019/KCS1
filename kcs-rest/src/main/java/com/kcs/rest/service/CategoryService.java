package com.kcs.rest.service;

import com.kcs.rest.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategory();

    Integer addCategory(String categoryName);

    Category findcategoryByName(String categoryName);

    List<Category> categoryData(int front, int back);

    Category showUpdateCategoryByID(int categoryID);

    Integer updateCategory(Category category);

    Integer delCategory(Category category);

    Integer countCategoryData();

    Category findOtherCategory(Integer categoryID, String categoryName);
}
