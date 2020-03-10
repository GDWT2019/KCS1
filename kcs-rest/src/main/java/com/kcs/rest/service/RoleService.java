package com.kcs.rest.service;

import com.kcs.rest.pojo.Role;
import com.kcs.rest.pojo.RolePresent;

import java.util.List;

public interface RoleService {

    List<Role> findAllRole( int begin, int end);

    Integer addRole(String roleName);

    Integer getRoleCount(String roleName);

    List<Role> findRoleByRoleName(int begin, int end, String roleName);

    Integer updateRole(Role role);

    Integer delRole(int roleID);

    List<Role> findTheOthersRoleByUserID(int userID);

    List<RolePresent> findAllRolePresent(int front, int back,int roleID);

    int findRolePresentCount(int roleID);

    Role findRoleByID(int roleID);
}
