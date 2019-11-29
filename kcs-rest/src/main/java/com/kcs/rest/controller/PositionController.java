package com.kcs.rest.controller;

import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Position;
import com.kcs.rest.service.PositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller("positionController")
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    //获取职位数据
    @RequestMapping(value="positionData")
    @ResponseBody
    public KcsResult UserData(){
        KcsResult result=positionService.findAllPosition();
        return result;
    }

}
