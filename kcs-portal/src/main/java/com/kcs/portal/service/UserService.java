package com.kcs.portal.service;

import com.kcs.rest.pojo.User;
import com.kcs.rest.pojo.UserPresent;
import com.kcs.rest.pojo.UserRole;

import java.util.List;

public interface UserService {
    List<User> findAllUser();

    List<UserPresent> findAllUserPresent();

    User findUserById(int id);

    User findByLoginName(String loginName);

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

    List<UserRole> findUserRoleByUserID(int userID);
}
