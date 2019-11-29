package com.kcs.rest.service;

import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.User;

import java.util.List;

public interface UserService {
    KcsResult findUserById(int id);

    KcsResult findByLoginName(String loginName);

    KcsResult findAllUser();

    KcsResult count();

    KcsResult findoneUser(String loginName);

    void updateBase(User user);

    void updatePass(User user);
}
