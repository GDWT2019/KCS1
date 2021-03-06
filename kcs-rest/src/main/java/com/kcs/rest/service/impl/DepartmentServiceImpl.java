package com.kcs.rest.service.impl;

import com.kcs.rest.dao.DepartmentDao;
import com.kcs.rest.pojo.Department;

import com.kcs.rest.dao.DepartmentDao;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Position;
import com.kcs.rest.service.DepartmentService;
import com.kcs.rest.utils.LogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> findAllDepartment() {
        return departmentDao.findAllDepartment();
    }

    @LogAnno(operateType ="新增部门" )
    @Override
    public Integer addDepartment(String departmentName) {
        return departmentDao.addDepartment(departmentName);
    }
}
