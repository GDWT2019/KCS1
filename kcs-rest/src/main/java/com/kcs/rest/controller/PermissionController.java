package com.kcs.rest.controller;

import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Permission;
import com.kcs.rest.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/addRolePermission")
    @ResponseBody
    public KcsResult addRolePermission(@RequestParam("roleID") String roleID, @RequestParam("permissionID") String permissionID){
        int rid = Integer.parseInt(roleID);
        int pid = Integer.parseInt(permissionID);
        Integer integer = permissionService.addRolePermission(rid, pid);
        return KcsResult.ok(integer);
    }

    @RequestMapping("/findTheOthersPermissionByRoleID{roleID}")
    @ResponseBody
    public KcsResult findTheOthersRoleByUserID(@PathVariable int roleID){
        return KcsResult.ok(permissionService.findTheOthersPermissionByRoleID(roleID));
    }

    @RequestMapping("/findAllPermission")
    @ResponseBody
    public KcsResult findAllPermission(@RequestParam("before")String before, @RequestParam("after")String after){
        int front = Integer.parseInt(before);
        int back = Integer.parseInt(after);

        List<Permission> allPermission = permissionService.findAllPermission(front,back);

        return KcsResult.ok(allPermission);
    }

    @RequestMapping("/permissionCount")
    @ResponseBody
    public KcsResult permissionCount(){
        Integer count = permissionService.permissionCount();
        return KcsResult.ok(count);
    }
}
