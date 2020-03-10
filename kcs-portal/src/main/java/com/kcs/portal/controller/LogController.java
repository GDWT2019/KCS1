package com.kcs.portal.controller;

import com.kcs.portal.service.UserService;
import com.kcs.portal.service.LogService;
import com.kcs.rest.pojo.LogPresent;
import com.kcs.rest.pojo.User;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller("logController")
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;

    @RequestMapping("/showAllLog")
    public String showLog(){
        return "LogData";
    }

    @RequestMapping(value = "/findAllLog",produces="text/html;charset=utf-8")
    @ResponseBody
    public String findLogByTimeUserID(HttpServletRequest request,String time1,String time2,String name){
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        int before=limit*(page-1)+1;
        int after = page * limit;

        List<LogPresent> LogPresentList = new ArrayList<>();
        int count = 0;
        JSONArray json =new JSONArray();
        String js = "";
        LogPresentList = logService.findLogByTimeName(before,after,time1,time2,name);
        count = count + logService.getLogCount(time1,time2,name);
        json =  JSONArray.fromObject(LogPresentList);
        js = js + json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";

        return jso;
    }
}
