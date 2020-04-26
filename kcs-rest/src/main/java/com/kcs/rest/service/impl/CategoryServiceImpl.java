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

    @Override
    public Category findcategoryByName(String categoryName) {
        return categoryDao.findcategoryByName(categoryName);
    }

    @Override
    public List<Category> categoryData(int front, int back) {
        return categoryDao.categoryData(front,back);
    }

    @Override
    public Category showUpdateCategoryByID(int categoryID) {
        return categoryDao.showUpdateCategoryByID(categoryID);
    }

    @Override
    public Integer updateCategory(Category category) {
        return categoryDao.updateCategory(category);
    }

    @Override
    public Integer delCategory(Category category) {
        return categoryDao.delCategory(category);
    }

    @Override
    public Integer countCategoryData() {
        return categoryDao.countCategoryData();
    }
}
