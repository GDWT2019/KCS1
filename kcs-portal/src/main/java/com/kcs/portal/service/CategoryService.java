package com.kcs.portal.service;

import com.kcs.rest.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategory();

    Integer addCategory(String categoryName);

    Category findcategoryByName(String categoryName);

}
