package com.kcs.rest.service.impl;

import com.kcs.rest.dao.UserDao;
import com.kcs.rest.dao.UserRoleDao;
import com.kcs.rest.pojo.Role;
import com.kcs.rest.pojo.User;
import com.kcs.rest.pojo.UserPresent;
import com.kcs.rest.pojo.UserRole;
import com.kcs.rest.service.UserService;
import com.kcs.rest.utils.LogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 根据id查找user，找到则返回KcsResult.ok(user),否则KcsResult.build(500,"未找到改用户数据！");
     * @param id
     * @return
     */
    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public User findByLoginName(String loginName) {
        User user = userDao.findByLoginName(loginName);
        if (user!=null)
            return user;
        return null;
     }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public List<UserPresent> findAllUserPresent(int front, int back, String name) {
        return userDao.findAllUserPresent(front,back,name);
    }

    @Override
    public int count(String name) {
        return userDao.count(name);
    }

    @Override
    public List<User> findoneUser(String loginName) {
        return  userDao.findoneUser(loginName);
    }

    @Override
    public void updateBase(User user) {
        userDao.updateBase(user);
    }

    @Override
    public void updatePass(User user) {
        userDao.updatePass(user);
    }

    @Override
    public List<User> findAlllister() {
        return userDao.findAlllister();
    }

    @Override
    public List<User> findAllWarehouse() {
        return userDao.findAllWarehouse();
    }

    @Override
    public List<User> AllUser() {
        return userDao.findAllUser();
    }

    @Override
    public List<User> findByName(String name) {
        return userDao.findByName(name);
    }

    @LogAnno(operateType = "新增用户")
    @Override
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

    @LogAnno(operateType = "删除用户")
    @Override
    public Integer delUserByUserID(int userID) {
        //先删除用户所拥有的角色
        List<UserRole> userRoleList = userRoleDao.findUserRoleByUserID(userID);
        if (userRoleList!=null){
            userRoleDao.delUserRoleByUserID_RoleID(userID,0);
        }
        return userDao.delUserByUserID(userID);
    }

    @Override
    public UserPresent findUserPresentById(int id) {
        return userDao.findUserPresentById(id);
    }

    @LogAnno(operateType = "修改用户基本信息")
    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public Integer lockUser(int userID, Boolean status) {
        return userDao.lockUser(userID,status);
    }


}
