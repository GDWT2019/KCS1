package com.kcs.portal.service.impl;

import com.kcs.portal.service.CategoryService;
import com.kcs.rest.pojo.Category;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> findAllCategory() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/category/getAllCategory");
            KcsResult result = KcsResult.formatToList(s,Category.class);
            if (result.getStatus() == 200) {
                return (List<Category>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
