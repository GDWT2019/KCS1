package com.kcs.rest.dao;

import com.kcs.rest.pojo.Department;

import com.kcs.rest.pojo.Position;

import java.util.List;

public interface DepartmentDao {

    //查找部门表所有记录
    List<Department> findAllDepartment();

    //新增部门
    Integer addDepartment(String departmentName);
}
