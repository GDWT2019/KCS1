package com.kcs.rest.dao;

import com.kcs.rest.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {
    List<Category> findAllCategory();

    Integer addCategory(String categoryName);


    Category findcategoryByName(String categoryName);

    List<Category> categoryData(@Param("front") int front, @Param("back") int back);

    Category showUpdateCategoryByID(int categoryID);

    Integer updateCategory(Category category);

    Integer delCategory(Category category);

    Integer countCategoryData();


    Category findOtherCategory(@Param("categoryID") Integer categoryID, @Param("categoryName") String categoryName);
}
