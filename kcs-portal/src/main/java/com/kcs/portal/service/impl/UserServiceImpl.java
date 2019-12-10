package com.kcs.portal.service.impl;

import com.kcs.portal.service.UserService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.User;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public List<User> findAllUser() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/user/userData");
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {


                return (List<User>) result.getData();
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
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/user/findUserById"+id);
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
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/user/loginUser"+loginName);
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
    public int count() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/user/findTotal");
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
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/user/getUser"+ loginName);
            KcsResult result = KcsResult.format(s);
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

            HttpClientUtil.doPostJson("http://localhost:8081/kcs_rest_war/user/updateBase", JsonUtils.objectToJson(user));
            System.out.println("jsonutils:  "+ JsonUtils.objectToJson(user));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePass(User user) {
        try {
            HttpClientUtil.doPostJson("http://localhost:8081/kcs_rest_war/user/updatePass", JsonUtils.objectToJson(user));
            System.out.println("jsonutils:  "+ JsonUtils.objectToJson(user));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAlllister() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/user/listerData");
            KcsResult result = KcsResult.format(s);
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
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/user/WarehouseData");
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {


                return (List<User>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
