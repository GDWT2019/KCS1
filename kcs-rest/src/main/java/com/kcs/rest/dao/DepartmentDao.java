package com.kcs.rest.dao;

import com.kcs.rest.pojo.Department;

import java.util.List;

public interface DepartmentDao {

    //查找部门表所有记录
    List<Department> findAllDepartment();
}
