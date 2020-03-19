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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
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
    @PreAuthorize("hasAnyAuthority('角色管理,角色添加,ROLE_ADMIN')")
    public AjaxMesg addRole(String roleName){

            Integer integer = roleService.addRole(roleName);
            if (integer<0)
                return new AjaxMesg(false,"新增角色失败！");
            return new AjaxMesg(true,"新增角色成功!");
    }

    @RequestMapping("/showRoleData")
    @PreAuthorize("hasAnyAuthority('角色管理,角色查看,ROLE_ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('角色管理,角色添加,ROLE_ADMIN')")
    public String showAddRole(){
        return "addRole";
    }

    @RequestMapping("/showAddUserRole")
    @PreAuthorize("hasAnyAuthority('用户管理,角色分配,ROLE_ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('用户管理,角色分配,ROLE_ADMIN')")
    public AjaxMesg addUserRole(int userID,int roleID){
        Integer i = roleService.addUserRole(userID, roleID);
        if (i<0)
            return new AjaxMesg(false,"新增用户角色失败！");
        return new AjaxMesg(false,"新增用户角色成功！");
    }

    @RequestMapping("/delUserRole")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('用户管理,角色删除,ROLE_ADMIN')")
    public AjaxMesg delUserRole(int userID,int roleID){
        Integer i = roleService.delUserRoleByUserID_RoleID(userID, roleID);if (i<0)
            return new AjaxMesg(false,"删除角色失败！");
        return new AjaxMesg(false,"删除角色成功！");
    }

    @RequestMapping("/showRolePermission")
    @PreAuthorize("hasAnyAuthority('角色管理,权限分配,ROLE_ADMIN')")
    public String showRolePermission(HttpServletRequest request,Model model){
        int roleID =Integer.valueOf(request.getParameter("roleID"));
        Role role = roleService.findRoleByID(roleID);
        model.addAttribute("role",role);
        return "rolePermission";
    }

    @RequestMapping(value = "/rolePermission",method= RequestMethod.GET,produces ="text/html;charset=utf-8")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('角色管理,权限分配,ROLE_ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('角色管理,权限分配,ROLE_ADMIN')")
    public String showAddRolePermission(HttpServletRequest request,Model model){
        int roleID =Integer.valueOf( request.getParameter("roleID"));
        Role role = roleService.findRoleByID(roleID);
        List<Permission> permissionList = permissionService.findTheOthersPermissionByRoleID(roleID);
        model.addAttribute("role",role);
        model.addAttribute("permissionList",permissionList);
        return "addRolePermission";
    }


    @RequestMapping("/showUpdateRole")
    @PreAuthorize("hasAnyAuthority('角色管理,角色修改,ROLE_ADMIN')")
    public String showUpdateRole(HttpServletRequest request,Model model){
        int roleID =Integer.valueOf( request.getParameter("roleID"));
        Role role = roleService.findRoleByID(roleID);
        model.addAttribute("role",role);
        return "updateRole";
    }

    @RequestMapping("/delRolePermission")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('角色查看,角色管理,角色删除,ROLE_ADMIN')")
    public AjaxMesg delRolePermission(int roleID,int permissionID){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Iterator<? extends GrantedAuthority> iterator = authentication.getAuthorities().iterator();
        boolean b =false;
        while (iterator.hasNext()){
            if (iterator.next().toString().equals("角色删除")){
                b = true;
            }
        }
        if(!b){
            return new AjaxMesg(false,"无删除权限,无法删除!");
        }
        int i = roleService.delRolePermission(roleID, permissionID);

        if (i<0)
            return new AjaxMesg(false,"删除失败");

        return new AjaxMesg(true,"删除成功！");

    }

    @RequestMapping("/updateRole")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('角色管理,角色修改,ROLE_ADMIN')")
    public AjaxMesg updateRole(Role role){

        int i = roleService.updateRole(role);

        if (i<0)
            return new AjaxMesg(false,"修改失败");

        return new AjaxMesg(true,"修改成功！");

    }

    @RequestMapping("/delRole")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('角色管理,角色删除,ROLE_ADMIN')")
    public AjaxMesg delRole(int roleID){

        int i = roleService.delRole(roleID);

        if (i<0)
            return new AjaxMesg(false,"删除失败");

        return new AjaxMesg(true,"删除成功！");

    }
}
