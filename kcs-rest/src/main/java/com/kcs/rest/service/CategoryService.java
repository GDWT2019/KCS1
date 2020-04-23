package com.kcs.rest.service;

import com.kcs.rest.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategory();

    Integer addCategory(String categoryName);

    Category findcategoryByName(String categoryName);

}
