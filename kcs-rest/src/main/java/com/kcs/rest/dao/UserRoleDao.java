package com.kcs.rest.dao;

import com.kcs.rest.pojo.UserRole;

import java.util.List;

public interface UserRoleDao {

    List<UserRole> findAllUserRole();

    Integer delByUserID(int userID);
}
