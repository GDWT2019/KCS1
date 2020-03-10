package com.kcs.rest.dao;

import com.kcs.rest.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleDao {

    List<UserRole> findAllUserRole();

    Integer delUserRoleByUserID_RoleID(@Param("userID") int userID, @Param("roleID") int roleID);

    List<UserRole> findUserRoleByUserID(int userID);

    Integer addUserRole(@Param("userID") int userID, @Param("roleID") int roleID);

    List<UserRole> findUserRoleByRoleID(int roleID);
}
