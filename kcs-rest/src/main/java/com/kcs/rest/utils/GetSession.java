package com.kcs.rest.utils;

import com.kcs.rest.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * 这个是保存request和session的类
 */
public class GetSession {
    public static User user = null;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        GetSession.user = user;
    }
}