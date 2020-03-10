package com.kcs.rest.service;

import com.kcs.rest.pojo.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAllPermission(int front, int back);

    Integer permissionCount();

    List<Permission> findTheOthersPermissionByRoleID(int roleID);

    Integer addRolePermission(int rid, int pid);
}
