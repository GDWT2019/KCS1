package com.kcs.rest.dao;

import com.kcs.rest.pojo.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAllCategory();
}
