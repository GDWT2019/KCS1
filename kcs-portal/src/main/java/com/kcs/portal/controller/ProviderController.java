package com.kcs.portal.controller;


import com.kcs.portal.service.ProviderService;
import com.kcs.rest.pojo.Provider;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("providerController")
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @RequestMapping(value="getProvider",produces="text/html;charset=utf-8")
    public @ResponseBody String getPosition(){
        List<Provider> list=providerService.findAllProvider();
        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        System.out.println(js);
        return js;
    }
}
