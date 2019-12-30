package com.kcs.portal.service;



import com.kcs.rest.pojo.Department;
import com.kcs.rest.pojo.Position;

import java.util.List;

public interface DepartmentService {
    List<Department> findAllDepartment();

    //新增部门
    Integer addDepartment(String departmentName);
}
