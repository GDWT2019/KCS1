package com.kcs.portal.service.impl;



import com.kcs.rest.pojo.Department;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Position;
import com.kcs.portal.service.DepartmentService;

import com.kcs.rest.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {


    @Override
    public List<Department> findAllDepartment() {
        try {
            String s = HttpClientUtil.doGet("http://192.168.10.33:8081/kcs_rest_war/department/departmentData");
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {

                return (List<Department>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
