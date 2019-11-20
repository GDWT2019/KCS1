package com.kcs.portal.service.impl;

import com.kcs.portal.service.UserService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.User;
import com.kcs.rest.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

//    @Autowired
//    private UserDao userDao;

    @Override
    public List<User> findAllUser() {
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

}
