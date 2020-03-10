package com.kcs.rest.service.impl;

import com.kcs.rest.dao.CategoryDao;
import com.kcs.rest.pojo.Category;
import com.kcs.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAllCategory() {
        return categoryDao.findAllCategory();
    }

    @Override
    public Integer addCategory(String categoryName) {
        return categoryDao.addCategory(categoryName);
    }
}
