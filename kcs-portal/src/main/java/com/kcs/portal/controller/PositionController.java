package com.kcs.portal.controller;



import com.kcs.portal.service.PositionService;
import com.kcs.rest.pojo.Position;
import net.sf.json.JSONArray;
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

    @RequestMapping(value="getPosition",produces="text/html;charset=utf-8")
    public @ResponseBody String getPosition(){
        List<Position> list=positionService.findAllPosition();
        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        System.out.println(js);
        return js;
    }
}
