package com.kcs.portal.service;

import com.kcs.rest.pojo.User;
import com.kcs.rest.pojo.UserPresent;
import com.kcs.rest.pojo.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface  UserService extends UserDetailsService {
    List<User> findAllUser();

    List<UserPresent> findAllUserPresent(int before, int after, String name);

    User findUserById(int id);

    User findByLoginName(String loginName);

    int count(String name);

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

    Integer lockUser(int userID,Boolean status);

    void sentSession(User user);
}
