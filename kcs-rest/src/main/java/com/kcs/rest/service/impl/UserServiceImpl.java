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
    public KcsResult findUserById(int id) {
        User user = userDao.findUserById(id);
        if (user != null)
            return KcsResult.ok(user);
        else
            return KcsResult.build(500,"未找到改用户数据！");
    }

    @Override
    public KcsResult findByLoginName(String loginName) {
        User user = userDao.findByLoginName(loginName);
        if(user!= null)
            return KcsResult.ok(user);
        else
            return KcsResult.build(500,"未找到该用户数据");

    }

    @Override
    public KcsResult findAllUser() {
        List<User> list=userDao.findAllUser();
        if(list != null)
            return KcsResult.ok(list);
        else
            return KcsResult.build(500,"未找到该用户数据");
    }

    @Override
    public KcsResult count() {
        int count=userDao.count();
        if(count > 0)
            return KcsResult.ok(count);
        else
            return KcsResult.build(500,"未找到该用户数据");
    }

    @Override
    public KcsResult findoneUser(String loginName) {
        List<User> list=userDao.findoneUser(loginName);
        if(list != null)
            return KcsResult.ok(list);
        else
            return KcsResult.build(500,"未找到该用户数据");
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
    public List<User> AllUser() {
        return userDao.findAllUser();
    }


}
