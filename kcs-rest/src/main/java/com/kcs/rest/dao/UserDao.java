package com.kcs.rest.dao;

import com.kcs.rest.pojo.User;
import com.kcs.rest.pojo.UserPresent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    User findUserById(int id);

    User findByLoginName(String loginName);

    List<UserPresent> findAllUserPresent(@Param("front") int front, @Param("back") int back, @Param("name") String name);

    List<User> findAllUser();

    int count(@Param("name") String name);

    List<User> findoneUser(String loginName);

    void updateBase(User user);

    void updatePass(User user);

    List<User> findAlllister();

    List<User> findAllWarehouse();

    List<User> findByName(@Param("name") String name);

    Integer addUser(User user);

    Integer delUserByUserID(int userID);

    UserPresent findUserPresentById(int id);

    Integer updateUser(User user);

    Integer lockUser(@Param("userID") int userID, @Param("status") Boolean status);
}