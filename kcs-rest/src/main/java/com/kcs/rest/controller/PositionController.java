package com.kcs.rest.controller;

import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Position;
import com.kcs.rest.service.PositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<Position> allPosition = positionService.findAllPosition();
        return KcsResult.ok(allPosition);
    }

    //新增职位数据
    @RequestMapping(value="addPosition{positionName}")
    @ResponseBody
    public KcsResult addPosition(@PathVariable String positionName){
        Integer i=positionService.addPosition(positionName);
        return KcsResult.ok(i);
    }

}
