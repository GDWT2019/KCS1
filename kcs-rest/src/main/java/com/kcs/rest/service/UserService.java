package com.kcs.rest.service;

import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.User;

import java.util.List;

public interface UserService {
    User findUserById(int id);

    User findByLoginName(String loginName);

    List<User> findAllUser();

    int count();

    List<User> findoneUser(String loginName);

    void updateBase(User user);

    void updatePass(User user);

    List<User> findAlllister();

    List<User> findAllWarehouse();
}
