package com.kcs.rest.service.impl;

import com.kcs.rest.dao.PermissionDao;
import com.kcs.rest.pojo.Permission;
import com.kcs.rest.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAllPermission(int front, int back) {
        return permissionDao.findAllPermission(front,back) ;
    }

    @Override
    public Integer permissionCount() {
        return permissionDao.permissionCount();
    }

    @Override
    public List<Permission> findTheOthersPermissionByRoleID(int roleID) {
        return permissionDao.findTheOthersPermissionByRoleID(roleID);
    }

    @Override
    public Integer addRolePermission(int rid, int pid) {
        return permissionDao.addRolePermission(rid,pid);
    }
}
