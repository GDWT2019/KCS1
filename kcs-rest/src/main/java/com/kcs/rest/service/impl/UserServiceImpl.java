package com.kcs.rest.service.impl;

import com.kcs.rest.dao.UserDao;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.User;
import com.kcs.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

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
        return userDao.findByLoginName(loginName);
            }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public int count() {
        return userDao.count();
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


}
