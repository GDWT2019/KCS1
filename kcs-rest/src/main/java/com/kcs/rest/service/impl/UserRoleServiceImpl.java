package com.kcs.rest.service.impl;

import com.kcs.rest.dao.UserRoleDao;
import com.kcs.rest.pojo.UserRole;
import com.kcs.rest.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> findUserRoleByUserID(int userID) {
        return userRoleDao.findUserRoleByUserID(userID);
    }

    @Override
    public Integer addUserRole(int userID, int roleID) {
        return userRoleDao.addUserRole(userID,roleID);
    }

    @Override
    public Integer delUserRoleByUserID_RoleID(int userID, int roleID) {
        return userRoleDao.delUserRoleByUserID_RoleID(userID,roleID);
    }

    @Override
    public List<UserRole> findUserRoleByRoleID(int roleID) {
        return userRoleDao.findUserRoleByRoleID(roleID);
    }
}
