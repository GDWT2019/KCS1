package com.kcs.rest.controller;

import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
