package com.kcs.portal.service.impl;

import com.kcs.portal.service.UserService;
import com.kcs.rest.pojo.*;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import com.kcs.rest.utils.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> findAllUser() {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/userData");
            KcsResult result = KcsResult.formatToList(s,User.class);
            if (result.getStatus() == 200) {
                return (List<User>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserPresent> findAllUserPresent(int before, int after, String name) {
        HashMap<String, String> param = new HashMap<>();
        param.put("before",before+"");
        param.put("after",after+"");
        param.put("name",name);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/userPresentData",param);
            KcsResult result = KcsResult.formatToList(s,UserPresent.class);
            if (result.getStatus() == 200) {
                return (List<UserPresent>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 传地址（url）和参数（id）给后端的UserController  /user/findUserById
     * @param id
     * @return
     */
    @Override
    public User findUserById(int id) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/findUserById"+id);
            KcsResult result = KcsResult.formatToPojo(s,User.class);
            if (result.getStatus() == 200) {
                User user = (User) result.getData();
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByLoginName(String loginName) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/loginUser"+loginName);
            KcsResult result = KcsResult.formatToPojo(s,User.class);
            if (result.getStatus() == 200) {
                User user = (User) result.getData();
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int count(String name) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"ser/findTotal"+name);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (int) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> findoneUser(String loginName) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/getUser"+ loginName);
            KcsResult result = KcsResult.formatToList(s,User.class);
            if (result.getStatus() == 200) {
                return (List<User>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateBase(User user) {
        try {
//            JSONObject jsonUser = JSONObject.fromObject(user);
//            String strUser = jsonUser.toString();

            HttpClientUtil.doPostJson(Rest.rest+"user/updateBase", JsonUtils.objectToJson(user));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePass(User user) {
        try {
            HttpClientUtil.doPostJson(Rest.rest+"user/updatePass", JsonUtils.objectToJson(user));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAlllister() {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/listerData");
            KcsResult result = KcsResult.formatToList(s,User.class);
            if (result.getStatus() == 200) {


                return (List<User>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAllWarehouse() {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/WarehouseData");
            KcsResult result = KcsResult.formatToList(s,User.class);
            if (result.getStatus() == 200) {


                return (List<User>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findByName(String name) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/findByName"+ name);
            KcsResult result = KcsResult.formatToList(s,User.class);
            if (result.getStatus() == 200) {
                return (List<User>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer addUser(User user) {
        try {
//            user.setUserID(1);
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            String s = HttpClientUtil.doPostJson(Rest.rest+"user/addUser", JsonUtils.objectToJson(user));
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delUserByUserID(int userID) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/delUserByUserID"+userID);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserPresent findUserPresentById(int id) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/findUserPresentById"+id);
            KcsResult result = KcsResult.formatToPojo(s,UserPresent.class);
            if (result.getStatus() == 200) {
                UserPresent userPresent = (UserPresent) result.getData();
                return userPresent;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateUser(User user) {
        try {
            String s = HttpClientUtil.doPostJson(Rest.rest+"user/updateUser", JsonUtils.objectToJson(user));
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserRole> findUserRoleByUserID(int userID) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/findUserRoleByUserID"+userID);
            KcsResult result = KcsResult.formatToList(s,UserRole.class);
            if (result.getStatus() == 200) {
                List<UserRole> userRoleList = (List<UserRole>) result.getData();
                return userRoleList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer lockUser(int userID, Boolean status) {
        HashMap<String, String> param = new HashMap<>();
        param.put("userID",userID+"");
        param.put("status",status+"");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/lockUser",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sentSession(User user) {
        try {
            HttpClientUtil.doPostJson(Rest.rest+"user/sentSession", JsonUtils.objectToJson(user));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"user/loginUser"+loginName);
            KcsResult result = KcsResult.formatToPojo(s,User.class);
            if (result.getStatus() == 200) {
                User user = (User) result.getData();
                org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User(user.getLoginName(),new BCryptPasswordEncoder().encode(user.getPassword()),user.isStatus(),true,true,true,getAuthority(user.getRoles()));
                return u;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /*public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : roles) {
//            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                list.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            }
        }
        return list;
    }*/
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
//            list.add(new SimpleGrantedAuthority(role.getRoleName()));
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                list.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            }
        }
        return list;
    }
}
