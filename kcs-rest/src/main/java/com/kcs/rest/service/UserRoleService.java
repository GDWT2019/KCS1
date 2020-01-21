package com.kcs.rest.service;

import com.kcs.rest.pojo.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> findUserRoleByUserID(int userID);

    Integer addUserRole(int userID, int roleID);

    Integer delUserRoleByUserID_RoleID(int userID, int roleID);

}
