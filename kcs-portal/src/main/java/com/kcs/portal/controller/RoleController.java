package com.kcs.portal.controller;

import com.kcs.portal.service.RoleService;
import com.kcs.rest.pojo.Role;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("roleController")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/showRoleData")
    public String showRoleData(){
        return "roleData";
    }

    @RequestMapping(value = "/roleData",produces="text/html;charset=utf-8")
    @ResponseBody
    public String findAllRole(HttpServletRequest request){
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        int before=limit*(page-1)+1;
        int after = page * limit;
        List<Role> LogPresentList = roleService.findAllRole(before,after);
        Integer count = roleService.getRoleCount(null);
        JSONArray json = JSONArray.fromObject(LogPresentList);
        String js=json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }


}
