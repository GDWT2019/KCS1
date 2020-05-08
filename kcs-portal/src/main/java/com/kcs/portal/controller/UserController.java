package com.kcs.portal.controller;

import com.kcs.portal.service.UserService;
import com.kcs.rest.pojo.User;
import com.kcs.rest.pojo.UserPresent;
import com.kcs.rest.pojo.UserRole;
import com.kcs.rest.utils.AjaxMesg;
import com.sun.org.apache.regexp.internal.RE;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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


    @RequestMapping("getInfo")
    @ResponseBody
    public Authentication getInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Iterator<? extends GrantedAuthority> iterator = authentication.getAuthorities().iterator();
        System.out.println(iterator);
        return authentication;
    }

    @RequestMapping("/loginCheck")
    @ResponseBody
    public AjaxMesg loginCheck(Model model,String loginName, String password){
        User user = userService.findByLoginName(loginName);
        if(!user.isStatus()){
            return new AjaxMesg(false,"该用户已被冻结！");
        }
        //System.err.println(user);
        if (user == null){
           return  new AjaxMesg(false,"该登录名不存在！");
        }
        if (!user.getPassword().equals(password)){
            return new AjaxMesg(false,"登录名或密码错误！");

        }
        return new AjaxMesg(true,"/login");
    }

    @RequestMapping("/success")
    @ResponseBody
    public String success(ModelAndView modelAndView, Model model, HttpServletRequest request, HttpSession session){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username =null;
        if (principal instanceof UserDetails) {
            username=((UserDetails)principal).getUsername();
        }
        User user = userService.findByLoginName(username);
//        model.addAttribute("user",user);
//        modelAndView.addObject("user",user);
        request.getSession().setAttribute("user",user);
        session.setAttribute("user",user);
        userService.sentSession((User)session.getAttribute("user"));
        return "index.jsp";
    }

    //修改用户密码
    @RequestMapping("/updatePass")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('用户修改,用户管理,ROLE_ADMIN')" )
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
        userService.updatePass(user);

    }

    //修改用户基本资料
    @RequestMapping("/updateBase")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('用户修改,用户管理,ROLE_ADMIN')" )
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
        userService.updateBase(user);

    }

    //上传图片
    //图片上传测试
    @RequestMapping("/upload")
    @ResponseBody
    public Map upload(MultipartFile file,HttpServletRequest request){

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
//                String filepath = "E:\\photoes\\" +uuid+"." + prefix;

                String path = request.getSession().getServletContext().getRealPath("/");
                String path1 = new File(path).getParent();
                String filepath = path1+"\\upload\\"+uuid+"." + prefix;
                File files=new File(filepath);

//                System.out.println(filepath);

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
        return js;
    }

    //返回用户数据页面

    @RequestMapping("/ruser")
    @PreAuthorize("hasAnyAuthority('用户查看,用户管理,ROLE_ADMIN')" )
    public String Ruser(){
        return "userData";
    }

    //获取用户数据
    @RequestMapping(value="userData",produces="text/html;charset=utf-8")
    @PreAuthorize("hasAnyAuthority('用户查看,用户管理,ROLE_ADMIN')" )
    public @ResponseBody String UserData(HttpServletRequest request,String name){
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        int before=limit*(page-1)+1;
        int after = page * limit;

        List<UserPresent> list=userService.findAllUserPresent(before,after,name);

        int count = 0;
        JSONArray json = null;
        String js="[]";
        if (list!=null){
            count = userService.count(name);
            json = JSONArray.fromObject(list);
            js = json.toString();
        }
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
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
    @PreAuthorize("hasAnyAuthority('用户修改,用户管理,ROLE_ADMIN')" )
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

   /* //登录
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
    }*/


    @RequestMapping("/findUserById")
    @ResponseBody
    public User findGoodsByGoodsID(HttpServletRequest request) {
        String userID = request.getParameter("userID");
        int userid = Integer.parseInt(userID);
        User user = userService.findUserById(userid);
        return user;
    }

    //返回新增用户页面
    @RequestMapping("/showAddUser")
    @PreAuthorize("hasAnyAuthority('用户添加,用户管理,ROLE_ADMIN')" )
    public String showAddUser(){
        return "addUser";
    }

    //判断是否存在该用户
    @RequestMapping("/loginNameExist")
    @ResponseBody
    public AjaxMesg loginNameExist(String loginName){
        User user = userService.findByLoginName(loginName);
        if (user==null){
            return new AjaxMesg(true,"该登录名可以使用！");
        }
        return new AjaxMesg(false,user.getLoginName());
    }

    //新增用户
    @RequestMapping("/addUser")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('用户添加,用户管理,ROLE_ADMIN')" )
    public AjaxMesg addUser(User user){
        Integer integer = userService.addUser(user);
        if (integer<0)
            return new AjaxMesg(false,"新增用户失败！");
        return new AjaxMesg(true,"新增用户成功!");
    }

    //删除用户
    @RequestMapping("/delUserByUserID")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('用户查看,用户删除,用户管理,ROLE_ADMIN')" )
    public AjaxMesg delUserByUserID(int userID){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Iterator<? extends GrantedAuthority> iterator = authentication.getAuthorities().iterator();
        boolean b =false;
        while (iterator.hasNext()){
            GrantedAuthority next = iterator.next();
            if ("用户删除".equals(next.getAuthority())||"用户管理".equals(next.getAuthority())||"ROLE_ADMIN".equals(next.getAuthority())){
                b = true;
            }
        }
        if(!b){
            return new AjaxMesg(false,"无删除权限,无法删除!");
        }
        Integer integer = userService.delUserByUserID(userID);
        if (integer<0)
            return new AjaxMesg(false,"删除失败！");
        return new AjaxMesg(true,"删除成功！");
    }

    @RequestMapping(value = "/toUpdateUser")
    public String toUpdateUser(HttpServletRequest request,Model model){
        int userID = Integer.valueOf(request.getParameter("userID"));
        UserPresent user = userService.findUserPresentById(userID);
        model.addAttribute("user",user);
        return "updateUser";
    }

    //跳转更新用户信息页面
    @RequestMapping(value = "/showUpdateUser",method= RequestMethod.POST,produces ="text/html;charset=utf-8")
    @ResponseBody
    public String showUpdateUser(int userID){
        UserPresent user = userService.findUserPresentById(userID);
        JSONArray json = JSONArray.fromObject(user);
        String js=json.toString();
        return js;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public AjaxMesg updateUser(User user){
        Integer i = userService.updateUser(user);
        if (i<0)
            return new AjaxMesg(false,"更新失败！");
        return new AjaxMesg(true,"更新成功");
    }

    //跳转用户角色详情页面
    @RequestMapping(value = "/showUserRole",method= RequestMethod.GET)
    public String showUserRole(HttpServletRequest request,Model model){
        int userID = Integer.valueOf(request.getParameter("userID"));
        UserPresent user = userService.findUserPresentById(userID);
        model.addAttribute("user",user);
        return "userRole";
    }

    //加载用户角色详情页面
    @RequestMapping(value = "/userRole",method= RequestMethod.GET,produces ="text/html;charset=utf-8")
    @ResponseBody
    public String userRole(HttpServletRequest request){
        int userID = Integer.valueOf(request.getParameter("userID"));
        List<UserRole> userRoleList = userService.findUserRoleByUserID(userID);
        String js= "";
        int count = 0;
        if (userRoleList == null){
            js ="[]";
        }
        else{
            JSONArray json = JSONArray.fromObject(userRoleList);
            js = json.toString();
            count = userRoleList.size();
        }
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }

    //冻结用户
    @RequestMapping("/lockUser")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('用户冻结,用户管理,ROLE_ADMIN')" )
    public AjaxMesg lockUser(int userID,Boolean status){
        Integer integer = userService.lockUser(userID,status);
        if (integer>0)
            if (status)
                return new AjaxMesg(true,"取消冻结成功！");
            else
                return new AjaxMesg(true,"冻结成功！");
        return new AjaxMesg(true,"冻结失败！");
    }



    @RequestMapping("/showLogin")
    public String showLogin() throws Exception {
        return "forward:/login.jsp";
    }
}
