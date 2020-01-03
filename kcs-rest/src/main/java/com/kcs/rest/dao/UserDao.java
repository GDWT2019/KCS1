package com.kcs.rest.dao;


import com.kcs.rest.pojo.User;
import com.kcs.rest.pojo.UserPresent;

import java.util.List;

public interface UserDao {
    User findUserById(int id);

    User findByLoginName(String loginName);

    List<UserPresent> findAllUserPresent();

    List<User> findAllUser();

    int count();

    List<User> findoneUser(String loginName);

    void updateBase(User user);

    void updatePass(User user);

    List<User> findAlllister();

    List<User> findAllWarehouse();

    List<User> findByName(String name);

    Integer addUser(User user);

    Integer delUserByUserID(int userID);

    UserPresent findUserPresentById(int id);

    Integer updateUser(User user);
}