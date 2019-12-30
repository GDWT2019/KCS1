package com.kcs.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.User;
import com.kcs.rest.pojo.UserPresent;
import com.kcs.rest.service.UserService;
import com.kcs.rest.utils.AjaxMesg;
import com.kcs.rest.utils.LogAnno;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //修改用户数密码
    @RequestMapping(value = "updatePass" ,method = RequestMethod.POST)
    @ResponseBody
    public  void updatePass(@RequestBody User user){
        userService.updatePass(user);

    }

    //修改用户数据
    @RequestMapping(value = "updateBase" ,method = RequestMethod.POST)
    @ResponseBody
    public  void updateBase(@RequestBody User user){
        userService.updateBase(user);

    }

    //查找某个用户的信息
    @RequestMapping("/getUser{loginName}")
    @ResponseBody
    public  KcsResult getUser(@PathVariable String loginName){
        List<User> users = userService.findoneUser(loginName);
        return KcsResult.ok(users);
    }

    //查找用户数据条数
    @RequestMapping(value="findTotal")
    @ResponseBody
    public  KcsResult findTotal(){
        int count = userService.count();
        return KcsResult.ok(count);
    }

    //获取用户数据
    @RequestMapping(value="userData")
    @ResponseBody
    public  KcsResult UserData(){
        List<User> allUser = userService.findAllUser();
        return KcsResult.ok(allUser);
    }

    //获取制表人数据
    @RequestMapping(value="listerData")
    @ResponseBody
    public  KcsResult listerData(){
        List<User> alllister = userService.findAlllister();
        return KcsResult.ok(alllister);
    }

    //获取仓管员数据
    @RequestMapping(value="WarehouseData")
    @ResponseBody
    public  KcsResult WarehouseData(){
        List<User> allWarehouse = userService.findAllWarehouse();
        return KcsResult.ok(allWarehouse);
    }


    //登录
    @RequestMapping("/loginUser{loginName}")
    @ResponseBody
    public KcsResult Login(@PathVariable String loginName){
        User byLoginName = userService.findByLoginName(loginName);
        return  KcsResult.ok(byLoginName);
    }
    /**
     * 使用url拼接参数，形参前加@PathVariable
     * @param id
     * @return
     */
    @RequestMapping("/findUserById{id}")
    @ResponseBody
    public KcsResult findUserById(@PathVariable int id){
        User userById = userService.findUserById(id);
        return  KcsResult.ok(userById);
    }

    //登录
    @RequestMapping("/findByName{name}")
    @ResponseBody
    public KcsResult findByName(@PathVariable String name){
        List<User> userList = userService.findByName(name);
        return  KcsResult.ok(userList);
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public KcsResult addUser(@RequestBody User user){
        return KcsResult.ok(userService.addUser(user));
    }

    @RequestMapping("/delUserByUserID{userID}")
    @ResponseBody
    public KcsResult delUserByUserID(@PathVariable int userID){
        return KcsResult.ok(userService.delUserByUserID(userID));
    }

    @RequestMapping("/findUserPresentById{id}")
    @ResponseBody
    public KcsResult findUserPresentById(@PathVariable int id){
        UserPresent userPresent = userService.findUserPresentById(id);
        return  KcsResult.ok(userPresent);
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public KcsResult updateUser(@RequestBody User user){
        return KcsResult.ok(userService.updateUser(user));
    }
}
