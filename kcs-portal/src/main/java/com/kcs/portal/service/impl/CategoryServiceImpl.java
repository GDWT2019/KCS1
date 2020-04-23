package com.kcs.portal.service.impl;

import com.kcs.portal.service.CategoryService;
import com.kcs.rest.pojo.Category;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.Rest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> findAllCategory() {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"category/getAllCategory");
            KcsResult result = KcsResult.formatToList(s,Category.class);
            if (result.getStatus() == 200) {
                return (List<Category>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer addCategory(String categoryName) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"category/addCategory"+categoryName);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {

                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category findcategoryByName(String categoryName) {
        try {
            String s = HttpClientUtil.doPost(Rest.rest+"category/findcategoryByName"+categoryName);
            KcsResult result = KcsResult.formatToPojo(s, Category.class);
            if (result.getStatus() == 200) {
                Category category = (Category) result.getData();
                return category;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
