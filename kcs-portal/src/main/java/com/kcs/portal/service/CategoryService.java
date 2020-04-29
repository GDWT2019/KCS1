package com.kcs.portal.service;

import com.kcs.rest.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategory();

    Integer addCategory(String categoryName);

    Category findcategoryByName(String categoryName);

    List<Category> categoryData(int before, int after);

    Category showUpdateCategoryByID(int categoryID);

    int updateCategory(Category category);

    int delCategory(Category category);

    int countCategoryData();


    Category findOtherCategory(Integer categoryID, String categoryName);
}
