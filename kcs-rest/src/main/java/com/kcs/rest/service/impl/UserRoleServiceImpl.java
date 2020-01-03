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
}
