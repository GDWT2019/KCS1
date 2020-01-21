package com.kcs.rest.dao;

import com.kcs.rest.pojo.Role;
import com.kcs.rest.pojo.RolePresent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {

    List<Role> findAllRole(@Param("begin") int begin, @Param("end") int end);

    Integer addRole(Role role);

    Integer getRoleCount(@Param("roleName") String roleName);

    List<Role> findRoleByRoleName(@Param("begin") int begin, @Param("end") int end,@Param("roleName") String roleName);

    Integer updateRole(Role role);

    Integer delRole(int roleID);

    List<Role> findTheOthersRoleByUserID(int userID);

    List<RolePresent> findAllRolePresent(@Param("front") int front, @Param("back") int back,@Param("roleID") int roleID);

    int findRolePresentCount(@Param("roleID") int roleID);

    Role findRoleByID(@Param("roleID") int roleID);
}
