package com.kcs.portal.service;



import com.kcs.rest.pojo.Department;
import com.kcs.rest.pojo.Position;

import java.util.List;

public interface DepartmentService {
    List<Department> findAllDepartment();
}
