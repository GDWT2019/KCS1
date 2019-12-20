package com.kcs.portal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcs.portal.service.UserService;
import com.kcs.rest.pojo.User;
import com.kcs.rest.utils.AjaxMesg;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //修改用户密码
    @RequestMapping("/updatePass")
    @ResponseBody
    public void updatePass(HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        User user =new User();
        //利用工具类将map封装为user对象
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        userService.updatePass(user);

    }

    //修改用户基本资料
    @RequestMapping("/updateBase")
    @ResponseBody
    public void updateBase(HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        User user =new User();
        //利用工具类将map封装为user对象
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        userService.updateBase(user);

    }

    //上传图片
    //图片上传测试
    @RequestMapping("/upload")
    @ResponseBody
    public Map upload(MultipartFile file){

        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                // dateStr = simpleDateFormat.format(date);
                String filepath = "E:\\photoes\\" +uuid+"." + prefix;

                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
//                map2.put("src","/photoes/"+ dateStr+"/"+uuid+"." + prefix);
                map2.put("src",uuid+"." + prefix);
//                map2.put("src","../../../static" + dateStr +uuid+ "." + prefix);
                System.out.println(map);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }

    //查找某个用户的信息
    @RequestMapping(value="getUser",produces="text/html;charset=utf-8")
    public @ResponseBody String getUser(HttpServletRequest request){
        String loginName = request.getParameter("loginName");
        List<User> list=userService.findoneUser(loginName);
        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        System.out.println(js);
        return js;
    }

    //返回用户数据页面
    @RequestMapping("/ruser")
    public String Ruser(){
        return "userData";
    }

    //获取用户数据
    @RequestMapping(value="userData",produces="text/html;charset=utf-8")
    public @ResponseBody String UserData(){
        /*int before=limit*(page-1)+1;
        int after = page * limit;*/

        List<User> list=userService.findAllUser();
        int count =userService.count();

        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;
    }

    //获取用户数据
    @RequestMapping(value="getAlluser",produces="text/html;charset=utf-8")
    public @ResponseBody String getAlluser(){
        List<User> list=userService.findAllUser();
        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        return js;
    }

    //获取制表人数据
    @RequestMapping(value="getAlllister",produces="text/html;charset=utf-8")
    public @ResponseBody String getAlllister(){
        List<User> list=userService.findAlllister();
        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        return js;
    }

    //获取仓管员数据
    @RequestMapping(value="getWarehouse",produces="text/html;charset=utf-8")
    public @ResponseBody String getWarehouse(){
        List<User> list=userService.findAllWarehouse();
        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        return js;
    }

    //返回安全设置
    @RequestMapping("/safeData")
    public String safeData(HttpServletRequest request){
        return "safe";
    }

    //返回基本资料
    @RequestMapping("/baseData")
    public String baseData(HttpServletRequest request){
        return "base";
    }

    //登出
    @RequestMapping("/loginOut")
    public void LoginOut(HttpServletRequest request,HttpServletResponse response){
        request.getSession().invalidate();
        try {
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //登录
    @RequestMapping("/loginUser")
    public void Login(HttpServletRequest request, HttpServletResponse response){
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        AjaxMesg ajaxMesg = new AjaxMesg();
        User user = userService.findByLoginName(loginName);
        if (user != null){
            if (password.equals(user.getPassword())){
                request.getSession().setAttribute("user",user);
                ajaxMesg.setFlag(true);
            }
            else
            {
                ajaxMesg.setFlag(false);
                ajaxMesg.setMesg("密码错误!");
            }
        }
        else
        {
            ajaxMesg.setFlag(false);
            ajaxMesg.setMesg("用户名不存在！");
        }

        ObjectMapper mapper =new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        try {
            mapper.writeValue(response.getWriter(),ajaxMesg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findUserById")
    @ResponseBody
    public User findGoodsByGoodsID(HttpServletRequest request) {
        String userID = request.getParameter("userID");
        int userid = Integer.parseInt(userID);
        User user = userService.findUserById(userid);
        System.out.println("User :"+ user);
        return user;
    }

}
