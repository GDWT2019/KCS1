package com.kcs.portal.service.impl;



import com.kcs.portal.service.DepartmentService;
import com.kcs.rest.pojo.Department;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.Rest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {


    @Override
    public List<Department> findAllDepartment() {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"department/departmentData");
            KcsResult result = KcsResult.formatToList(s,Department.class);
            if (result.getStatus() == 200) {

                return (List<Department>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer addDepartment(String departmentName) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"department/addDepartment"+departmentName);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {

                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
