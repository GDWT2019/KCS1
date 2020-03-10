package com.kcs.portal.controller;

import com.kcs.portal.service.PermissionService;
import com.kcs.rest.pojo.Permission;
import com.kcs.rest.utils.AjaxMesg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/addRolePermission")
    @ResponseBody
    public AjaxMesg addRolePermission(int roleID, int permissionID){
        Integer i = permissionService.addRolePermission(roleID, permissionID);
        if (i<0)
            return new AjaxMesg(false,"新增权限失败！");
        return new AjaxMesg(false,"新增权限成功！");
    }

    @RequestMapping("/showPermissionData")
    public String showPermissionData(){
        return "permissionData";
    }

    @RequestMapping(value = "/permissionData",method= RequestMethod.GET,produces ="text/html;charset=utf-8")
    @ResponseBody
    public String permissionData(HttpServletRequest request){
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        int before=limit*(page-1)+1;
        int after = page * limit;
        List<Permission> permissionList = permissionService.findAllPermission(before,after);
        Integer count = permissionService.getPermissionCount();
        JSONArray json = JSONArray.fromObject(permissionList);
        String js=json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }
}
