package com.kcs.portal.controller;

import com.kcs.portal.service.UserService;
import com.kcs.rest.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据id查找用户
     * @param model
     * @return
     */
    @RequestMapping("/findUserById")
    public String findUserById(Model model){
        User user = userService.findUserById(1);
        if (user != null){
            model.addAttribute("user",user);
            return "result";
        }
        return "none";
    }
}
