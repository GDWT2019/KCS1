package com.kcs.rest.controller;

import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller("logController")
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/findAllLog",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult findAllLog(@RequestParam("begin")String begin, @RequestParam("end")String end ){
        int front = Integer.parseInt(begin);
        int back = Integer.parseInt(end);
        return KcsResult.ok(logService.findAllLog(front,back));
    }

    @RequestMapping(value = "/getLogCount",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult getLogCount(@RequestParam("time1")String time1, @RequestParam("time2") String time2, @RequestParam("userID") String userID){
        int uid = Integer.parseInt(userID);
        return KcsResult.ok(logService.getLogCount(time1,time2,uid));
    }

    @RequestMapping(value = "/findLogByTimeUserID",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult findLogByTimeUserID(@RequestParam("begin")String begin, @RequestParam("end")String end, @RequestParam("time1")String time1, @RequestParam("time2") String time2, @RequestParam("userID") String userID){
        int front = Integer.parseInt(begin);
        int back = Integer.parseInt(end);
        int uid = Integer.parseInt(userID);
        return KcsResult.ok(logService.findLogByTimeUserID(front, back, time1, time2, uid));
    }
}
