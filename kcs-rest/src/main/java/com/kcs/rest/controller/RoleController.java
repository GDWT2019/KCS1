package com.kcs.rest.controller;

import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Role;
import com.kcs.rest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("roleController")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/findAllRole",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult findAllRole(@RequestParam("begin")String begin, @RequestParam("end")String end ){
        int front = Integer.parseInt(begin);
        int back = Integer.parseInt(end);
        return KcsResult.ok(roleService.findAllRole(front,back));
    }

    @RequestMapping(value = "/getRoleCount",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult getRoleCount( @RequestParam("roleName") String roleName){
        System.out.println("3333333333333:"+roleName);
        return KcsResult.ok(roleService.getRoleCount(roleName));
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public KcsResult addRole(@RequestBody Role role){
        return KcsResult.ok(roleService.addRole(role));
    }

    @RequestMapping(value = "/findRoleByRoleName",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult findRoleByRoleName(@RequestParam("begin")String begin, @RequestParam("end")String end,  @RequestParam("roleName") String roleName){
        int front = Integer.parseInt(begin);
        int back = Integer.parseInt(end);
        return KcsResult.ok(roleService.findRoleByRoleName(front, back, roleName));
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public KcsResult updateRole(@RequestBody Role role){
        return KcsResult.ok(roleService.updateRole(role));
    }

    @RequestMapping("/delRole{roleID}")
    @ResponseBody
    public KcsResult delRole(@PathVariable int roleID){
        return KcsResult.ok(roleService.delRole(roleID));
    }
}
