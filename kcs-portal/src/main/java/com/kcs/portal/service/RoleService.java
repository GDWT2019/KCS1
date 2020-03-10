package com.kcs.portal.service;

import com.kcs.rest.pojo.Role;
import com.kcs.rest.pojo.RolePresent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {

    List<Role> findAllRole(@Param("begin") int begin, @Param("end") int end);

    Integer addRole(String roleName);

    Integer getRoleCount(String roleName);

    List<Role> findRoleByRoleName(@Param("begin") int begin, @Param("end") int end,@Param("roleName") String roleName);

    Integer updateRole(Role role);

    Integer delRole(int roleID);

    List<Role> findTheOthersRoleByUserID(int userID);

    Integer addUserRole(int userID, int roleID);

    Integer delUserRoleByUserID_RoleID(int userID, int roleID);

    List<RolePresent> findAllRolePresent(int front, int back, int roleID);

    int findRolePresentCount(int roleID);

    Role findRoleByID(int roleID);
}
