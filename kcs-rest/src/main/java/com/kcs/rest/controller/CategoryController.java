package com.kcs.rest.controller;

import com.kcs.rest.pojo.Category;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("categoryController")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/findcategoryByName{categoryName}")
    @ResponseBody
    public KcsResult findcategoryByName(@PathVariable String categoryName){
        Category category = categoryService.findcategoryByName(categoryName);
        return KcsResult.ok(category);
    }

    //新增类别数据
    @RequestMapping(value="addCategory{categoryName}")
    @ResponseBody
    public KcsResult addCategory(@PathVariable String categoryName){
        Integer i=categoryService.addCategory(categoryName);
        return KcsResult.ok(i);
    }

    @RequestMapping("/getAllCategory")
    @ResponseBody
    public KcsResult getAllCategory(){
        List<Category> categoryList = categoryService.findAllCategory();
        return KcsResult.ok(categoryList);
    }
}
