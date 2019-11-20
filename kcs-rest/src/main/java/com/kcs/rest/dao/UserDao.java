package com.kcs.rest.dao;


import com.kcs.rest.pojo.User;
import java.util.List;

public interface UserDao {
    User findUserById(int id);
}