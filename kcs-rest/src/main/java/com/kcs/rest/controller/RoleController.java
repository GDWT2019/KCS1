package com.kcs.rest.controller;

import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Role;
import com.kcs.rest.pojo.RolePresent;
import com.kcs.rest.pojo.UserRole;
import com.kcs.rest.service.RoleService;
import com.kcs.rest.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("roleController")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;


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

    @RequestMapping("/findTheOthersRoleByUserID{userID}")
    @ResponseBody
    public KcsResult findTheOthersRoleByUserID(@PathVariable int userID){
        return KcsResult.ok(roleService.findTheOthersRoleByUserID(userID));
    }

    @RequestMapping("/addUserRole")
    @ResponseBody
    public KcsResult addUserRole(@RequestParam("userID") String userID, @RequestParam("roleID") String roleID){
        int uid = Integer.parseInt(userID);
        int rid = Integer.parseInt(roleID);
        Integer integer = userRoleService.addUserRole(uid, rid);
        return KcsResult.ok(integer);
    }

    @RequestMapping("/delUserRoleByUserID_RoleID")
    @ResponseBody
    public KcsResult delUserRoleByUserID_RoleID(@RequestParam("userID") String userID, @RequestParam("roleID") String roleID){
        int uid = Integer.parseInt(userID);
        int rid = Integer.parseInt(roleID);
        Integer integer = userRoleService.delUserRoleByUserID_RoleID(uid, rid);
        return KcsResult.ok(integer);
    }

    @RequestMapping("/findAllRolePresent")
    @ResponseBody
    public KcsResult findAllRolePresent(@RequestParam("begin") String begin,@RequestParam("end") String end,@RequestParam("roleID") int roleID){
        int front = Integer.parseInt(begin);
        int back = Integer.parseInt(end);
        List<RolePresent> rolePresent = roleService.findAllRolePresent(front, back, roleID);
        return KcsResult.ok(rolePresent);
    }

    @RequestMapping(value = "/findRolePresentCount",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult findRolePresentCount( @RequestParam("roleID") String roleID){
        int rid = Integer.parseInt(roleID);
        return KcsResult.ok(roleService.findRolePresentCount(rid));
    }

    @RequestMapping("/findRoleByID{roleID}")
    @ResponseBody
    public KcsResult findRoleByID(@PathVariable int roleID){
        Role role = roleService.findRoleByID(roleID);
        return KcsResult.ok(role);
    }
}
