package com.kcs.portal.controller;

import com.kcs.portal.service.PermissionService;
import com.kcs.portal.service.RoleService;
import com.kcs.portal.service.UserService;
import com.kcs.rest.pojo.Permission;
import com.kcs.rest.pojo.Role;
import com.kcs.rest.pojo.RolePresent;
import com.kcs.rest.pojo.User;
import com.kcs.rest.utils.AjaxMesg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("roleController")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/addRole")
    @ResponseBody
    public AjaxMesg addRole(String roleName){

            Integer integer = roleService.addRole(roleName);
            if (integer<0)
                return new AjaxMesg(false,"新增用户失败！");
            return new AjaxMesg(true,"新增用户成功!");
    }

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

    @RequestMapping("/showAddRole")
    public String showAddRole(){
        return "addRole";
    }

    @RequestMapping("/showAddUserRole")
    public String showAddUserRole(HttpServletRequest request, Model model){
        int userID =Integer.valueOf( request.getParameter("userID"));
        User user = userService.findUserById(userID);
        List<Role> roleList = roleService.findTheOthersRoleByUserID(userID);
        model.addAttribute("user",user);
        model.addAttribute("roleList",roleList);
        return "addUserRole";
    }

    @RequestMapping("/addUserRole")
    @ResponseBody
    public AjaxMesg addUserRole(int userID,int roleID){
        Integer i = roleService.addUserRole(userID, roleID);
        if (i<0)
            return new AjaxMesg(false,"新增角色失败！");
        return new AjaxMesg(false,"新增角色成功！");
    }

    @RequestMapping("/delUserRole")
    @ResponseBody
    public AjaxMesg delUserRole(int userID,int roleID){
        Integer i = roleService.delUserRoleByUserID_RoleID(userID, roleID);if (i<0)
            return new AjaxMesg(false,"删除角色失败！");
        return new AjaxMesg(false,"删除角色成功！");
    }

    @RequestMapping("/showRolePermission")
    public String showRolePermission(HttpServletRequest request,Model model){
        int roleID =Integer.valueOf(request.getParameter("roleID"));
        Role role = roleService.findRoleByID(roleID);
        model.addAttribute("role",role);
        return "rolePermission";
    }

    @RequestMapping(value = "/rolePermission",method= RequestMethod.GET,produces ="text/html;charset=utf-8")
    @ResponseBody
    public String RolePermission(HttpServletRequest request,Model model){
        int roleID = Integer.valueOf(request.getParameter("roleID"));
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        int before=limit*(page-1)+1;
        int after = page * limit;
        List<RolePresent> allRolePresent = roleService.findAllRolePresent(before, after, roleID);
        int count = roleService.findRolePresentCount(roleID);
        String js=null;
        if (allRolePresent != null){
            JSONArray json = JSONArray.fromObject(allRolePresent);
            js = json.toString();
        }
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }

    @RequestMapping("/showAddRolePermission")
    public String showAddRolePermission(HttpServletRequest request,Model model){
        int roleID =Integer.valueOf( request.getParameter("roleID"));
        Role role = roleService.findRoleByID(roleID);
        List<Permission> permissionList = permissionService.findTheOthersPermissionByRoleID(roleID);
        model.addAttribute("role",role);
        model.addAttribute("permissionList",permissionList);
        return "addRolePermission";
    }


    @RequestMapping("/showUpdateRole")
    public String showUpdateRole(HttpServletRequest request,Model model){
        int roleID =Integer.valueOf( request.getParameter("roleID"));
        Role role = roleService.findRoleByID(roleID);
        model.addAttribute("role",role);
        return "updateRole";
    }

    @RequestMapping("/delRolePermission")
    @ResponseBody
    public AjaxMesg delRolePermission(int roleID,int permissionID){

        int i = roleService.delRolePermission(roleID, permissionID);

        if (i<0)
            return new AjaxMesg(false,"删除失败");

        return new AjaxMesg(true,"删除成功！");

    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public AjaxMesg updateRole(Role role){

        int i = roleService.updateRole(role);

        if (i<0)
            return new AjaxMesg(false,"修改失败");

        return new AjaxMesg(true,"修改成功！");

    }

    @RequestMapping("/delRole")
    @ResponseBody
    public AjaxMesg delRole(int roleID){

        int i = roleService.delRole(roleID);

        if (i<0)
            return new AjaxMesg(false,"删除失败");

        return new AjaxMesg(true,"删除成功！");

    }
}
