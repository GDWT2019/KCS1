package com.kcs.portal.service;

import com.kcs.rest.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();

    User findUserById(int id);

    User findByLoginName(String loginName);

    int count();

    List<User> findoneUser(String loginName);

    void updateBase(User user);

    void updatePass(User user);

    List<User> findAlllister();

    List<User> findAllWarehouse();
}
