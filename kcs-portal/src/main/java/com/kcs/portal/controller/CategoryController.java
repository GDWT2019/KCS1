package com.kcs.portal.controller;

import com.kcs.portal.service.CategoryService;
import com.kcs.rest.pojo.Category;
import com.kcs.rest.utils.AjaxMesg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("categoryController")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/rCategory")
    public String rCategory() {
        return "addCategory";
    }

    @RequestMapping("/addCategory")
    @ResponseBody
    public AjaxMesg addCategory(String categoryName){

        Category category=categoryService.findcategoryByName(categoryName);
        if (category==null){
            Integer integer = categoryService.addCategory(categoryName);
            if (integer<1){
                return new AjaxMesg(false,"新增类别失败！");
            }
            return new AjaxMesg(true,"新增类别成功！");
        }else{
            return new AjaxMesg(true,"增加失败，类别已存在！");
        }
    }

    @RequestMapping(value="getCategory",produces="text/html;charset=utf-8")
    public @ResponseBody  String getCategory(){
        List<Category> list=categoryService.findAllCategory();
        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        System.out.println(js);
        return js;
    }
}
