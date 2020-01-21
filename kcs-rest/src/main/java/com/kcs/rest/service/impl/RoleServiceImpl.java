package com.kcs.rest.service.impl;

import com.kcs.rest.dao.RoleDao;
import com.kcs.rest.pojo.Role;
import com.kcs.rest.pojo.RolePresent;
import com.kcs.rest.service.RoleService;
import com.kcs.rest.utils.LogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @LogAnno(operateType = "查询角色")
    @Override
    public List<Role> findAllRole(int begin, int end) {
        return roleDao.findAllRole(begin,end);
    }

    @LogAnno(operateType = "新增角色")
    @Override
    public Integer addRole(Role role) {
        return roleDao.addRole(role);
    }

    @Override
    public Integer getRoleCount(String roleName) {
        return roleDao.getRoleCount(roleName);
    }

    @LogAnno(operateType = "查询角色")
    @Override
    public List<Role> findRoleByRoleName(int begin, int end, String roleName) {
        return roleDao.findRoleByRoleName(begin, end, roleName);
    }

    @LogAnno(operateType = "修改角色")
    @Override
    public Integer updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @LogAnno(operateType = "删除角色")
    @Override
    public Integer delRole(int roleID) {
        return roleDao.delRole(roleID);
    }

    @Override
    public List<Role> findTheOthersRoleByUserID(int userID) {
        return roleDao.findTheOthersRoleByUserID(userID);
    }

    @Override
    public List<RolePresent> findAllRolePresent(int front, int back, int roleID) {
        return roleDao.findAllRolePresent(front, back,roleID);
    }

    @Override
    public int findRolePresentCount(int roleID) {
        return roleDao.findRolePresentCount(roleID);
    }

    @Override
    public Role findRoleByID(int roleID) {
        return roleDao.findRoleByID(roleID);
    }
}
