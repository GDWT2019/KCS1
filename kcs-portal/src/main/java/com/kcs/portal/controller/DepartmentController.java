package com.kcs.portal.controller;




import com.kcs.portal.service.DepartmentService;
import com.kcs.rest.pojo.Department;
import com.kcs.rest.utils.AjaxMesg;
import net.sf.json.JSONArray;
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

    @RequestMapping(value="getDepartment",produces="text/html;charset=utf-8")
    public @ResponseBody
    String getPosition(){
        List<Department> list=departmentService.findAllDepartment();
        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        System.out.println(js);
        return js;
    }

    @RequestMapping("/addDepartment")
    @ResponseBody
    public AjaxMesg addDepartment(String departmentName){
        Integer integer = departmentService.addDepartment(departmentName);
        if (integer<1){
            return new AjaxMesg(false,"新增部门失败！");
        }
        return new AjaxMesg(true,"新增部门成功！");
    }
}
