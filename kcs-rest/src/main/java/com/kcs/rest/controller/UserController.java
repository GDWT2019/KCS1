package com.kcs.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.User;
import com.kcs.rest.service.UserService;
import com.kcs.rest.utils.AjaxMesg;
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
        System.out.println("user:   "+user);
        userService.updatePass(user);

    }

    //修改用户数据
    @RequestMapping(value = "updateBase" ,method = RequestMethod.POST)
    @ResponseBody
    public  void updateBase(@RequestBody User user){
        System.out.println("user:   "+user);
        userService.updateBase(user);

    }

    //查找某个用户的信息
    @RequestMapping("/getUser{loginName}")
    @ResponseBody
    public  KcsResult getUser(@PathVariable String loginName){

        KcsResult result=userService.findoneUser(loginName);
        return result;
    }

    //查找用户数据条数
    @RequestMapping(value="findTotal")
    @ResponseBody
    public  KcsResult findTotal(){
        KcsResult result=userService.count();
        return result;
    }

    //获取用户数据
    @RequestMapping(value="userData")
    @ResponseBody
    public  KcsResult UserData(){
        KcsResult result=userService.findAllUser();
        return result;
    }

    //登录
    @RequestMapping("/loginUser{loginName}")
    @ResponseBody
    public KcsResult Login(@PathVariable String loginName){
        KcsResult result = userService.findByLoginName(loginName);
       return result;
    }
    /**
     * 使用url拼接参数，形参前加@PathVariable
     * @param id
     * @return
     */
    @RequestMapping("/findUserById{id}")
    @ResponseBody
    public KcsResult findUserById(@PathVariable int id){
        KcsResult result = userService.findUserById(id);
        return result;
    }

}
