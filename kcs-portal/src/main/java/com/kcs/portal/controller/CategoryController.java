package com.kcs.portal.controller;

import com.kcs.portal.service.CategoryService;
import com.kcs.rest.pojo.Category;
import com.kcs.rest.pojo.Goods;
import com.kcs.rest.utils.AjaxMesg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("categoryController")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/delCategory")
    @ResponseBody
//    @PreAuthorize("hasAnyAuthority('角色管理,角色修改,ROLE_ADMIN')")
    public AjaxMesg delCategory(Category category){

        int i = categoryService.delCategory(category);

        if (i<0)
            return new AjaxMesg(false,"删除失败");

        return new AjaxMesg(true,"删除成功！");

    }

    @RequestMapping("/updateCategory")
    @ResponseBody
//    @PreAuthorize("hasAnyAuthority('角色管理,角色修改,ROLE_ADMIN')")
    public AjaxMesg updateCategory(Category category){
        Category category1=categoryService.findOtherCategory(category.getCategoryID(),category.getCategoryName());
        if (category1==null) {
            int i = categoryService.updateCategory(category);
            if (i < 0)
                return new AjaxMesg(false, "修改失败");

            return new AjaxMesg(true, "修改成功！");
        }else {
            return new AjaxMesg(false, "修改失败,类别已存在");
        }

    }

    @RequestMapping(value = "/showUpdateCategoryByID",method= RequestMethod.POST,produces ="text/html;charset=utf-8")
    @ResponseBody
    public String showUpdateCategoryByID(int categoryID){
        Category category = categoryService.showUpdateCategoryByID(categoryID);
        JSONArray json = JSONArray.fromObject(category);
        String js=json.toString();
        return js;
    }

    @RequestMapping("/findCategoryByID")
    @ResponseBody
    public Category findCategoryByID(int categoryID){
        Category category = categoryService.showUpdateCategoryByID(categoryID);

        return category;
    }

    @RequestMapping("/showUpdatecategory")
    public String showUpdatecategory() {
        return "updateCategory";
    }

    @RequestMapping(value = "/categoryData",produces="text/html;charset=utf-8")
    @ResponseBody
    public String categoryData(HttpServletRequest request){
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        int before=limit*(page-1)+1;
        int after = page * limit;
        List<Category> categoryDataList = categoryService.categoryData(before,after);
        int count = categoryService.countCategoryData();
        JSONArray json = JSONArray.fromObject(categoryDataList);
        String js=json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }

    @RequestMapping("/showCategoryData")
    public String showCategoryData() {
        return "categoryData";
    }

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
