package com.kcs.rest.service.impl;

import com.kcs.rest.dao.UserDao;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.User;
import com.kcs.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
