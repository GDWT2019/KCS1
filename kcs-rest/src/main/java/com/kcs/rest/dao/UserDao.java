package com.kcs.rest.dao;


import com.kcs.rest.pojo.User;
import java.util.List;

public interface UserDao {
    User findUserById(int id);

    User findByLoginName(String loginName);

    List<User> findAllUser();

    int count();

    List<User> findoneUser(String loginName);

    void updateBase(User user);

    void updatePass(User user);
}