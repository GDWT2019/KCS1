package com.kcs.rest.service;

import com.kcs.rest.pojo.Department;

import com.kcs.rest.pojo.Department;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Position;

import java.util.List;

public interface DepartmentService {
    //查找部门表所有记录
    List<Department> findAllDepartment();

    //新增部门
    Integer addDepartment(String departmentName);
}
