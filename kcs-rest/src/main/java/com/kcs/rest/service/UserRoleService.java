package com.kcs.rest.service;

import com.kcs.rest.pojo.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> findUserRoleByUserID(int userID);
}
