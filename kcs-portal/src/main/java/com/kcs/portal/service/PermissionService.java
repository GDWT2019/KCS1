package com.kcs.portal.service;

import com.kcs.rest.pojo.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAllPermission(int before, int after);

    Integer getPermissionCount();


    List<Permission> findTheOthersPermissionByRoleID(int roleID);

    Integer addRolePermission(int roleID, int permissionID);
}
