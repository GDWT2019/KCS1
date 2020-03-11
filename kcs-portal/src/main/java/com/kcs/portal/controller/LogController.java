package com.kcs.portal.controller;

import com.kcs.portal.service.UserService;
import com.kcs.portal.service.LogService;
import com.kcs.rest.pojo.LogPresent;
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
        return "logData";
    }

    @RequestMapping(value = "/findAllLog",produces="text/html;charset=utf-8")
    @ResponseBody
    public String findLogByTimeUserID(HttpServletRequest request,String time1,String time2,String name){
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        int before=limit*(page-1)+1;
        int after = page * limit;

        List<LogPresent> LogPresentList = new ArrayList<>();
        int count = logService.getLogCount(time1,time2,name);

        LogPresentList = logService.findLogByTimeName(before,after,time1,time2,name);
        JSONArray json =  JSONArray.fromObject(LogPresentList);
        String js =json.toString();

        if(LogPresentList == null ){
            count = 0;
            js = "[]";
        }
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }
}
