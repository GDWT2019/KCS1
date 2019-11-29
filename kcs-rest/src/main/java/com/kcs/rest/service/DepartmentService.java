package com.kcs.rest.service;

import com.kcs.rest.pojo.Department;

import java.util.List;

public interface DepartmentService {
    //查找部门表所有记录
    List<Department> findAllDepartment();
}
