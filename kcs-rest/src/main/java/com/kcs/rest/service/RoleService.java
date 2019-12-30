package com.kcs.rest.service;

import com.kcs.rest.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {

    List<Role> findAllRole(@Param("begin") int begin, @Param("end") int end);

    Integer addRole(Role role);

    Integer getRoleCount(String roleName);

    List<Role> findRoleByRoleName(@Param("begin") int begin, @Param("end") int end,@Param("roleName") String roleName);

    Integer updateRole(Role role);

    Integer delRole(int roleID);
}
