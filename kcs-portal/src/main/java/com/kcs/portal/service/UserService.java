package com.kcs.portal.service;

import com.kcs.rest.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
    User findUserById(int id);
}
