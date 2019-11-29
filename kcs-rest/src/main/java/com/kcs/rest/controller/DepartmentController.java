package com.kcs.rest.controller;



import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("departmentController")
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    //获取部门数据
    @RequestMapping(value="departmentData")
    @ResponseBody
    public KcsResult UserData(){
        KcsResult result=departmentService.findAllDepartment();
        return result;
    }
}
