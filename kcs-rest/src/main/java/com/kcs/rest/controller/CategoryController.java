package com.kcs.rest.controller;

import com.kcs.rest.pojo.Category;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("categoryController")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/countCategoryData")
    @ResponseBody
    public KcsResult countCategoryData(){
        return KcsResult.ok(categoryService.countCategoryData());
    }

    @RequestMapping("/delCategory")
    @ResponseBody
    public KcsResult delGoods(@RequestBody Category category){
        return KcsResult.ok(categoryService.delCategory(category));
    }

    @RequestMapping("/updateCategory")
    @ResponseBody
    public KcsResult updateCategory(@RequestBody Category category){
        return KcsResult.ok(categoryService.updateCategory(category));
    }

    @RequestMapping("/showUpdateCategoryByID{categoryID}")
    @ResponseBody
    public KcsResult showUpdateCategoryByID(@PathVariable int categoryID){
        Category category = categoryService.showUpdateCategoryByID(categoryID);
        return  KcsResult.ok(category);
    }

    @RequestMapping(value = "/categoryData",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult categoryData(@RequestParam("front")String before, @RequestParam("back")String after ){
        int front = Integer.parseInt(before);
        int back = Integer.parseInt(after);
        return KcsResult.ok(categoryService.categoryData(front,back));
    }

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

    @RequestMapping("/findOtherCategory")
    @ResponseBody
    public KcsResult findOtherCategory(@RequestParam("categoryID") Integer categoryID,@RequestParam("categoryName") String categoryName){
        Category category = categoryService.findOtherCategory(categoryID, categoryName);
        return KcsResult.ok(category);

    }
}
