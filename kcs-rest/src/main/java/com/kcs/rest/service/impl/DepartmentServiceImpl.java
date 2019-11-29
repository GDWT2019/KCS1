package com.kcs.rest.service.impl;


import com.kcs.rest.dao.DepartmentDao;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Position;
import com.kcs.rest.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public KcsResult findAllDepartment() {
        List<Position> list=departmentDao.findAllDepartment();
        if(list != null)
            return KcsResult.ok(list);
        else
            return KcsResult.build(500,"未找到该用户数据");
    }
}
