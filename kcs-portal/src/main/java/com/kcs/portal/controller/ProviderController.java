package com.kcs.portal.controller;


import com.kcs.portal.service.ProviderService;
import com.kcs.rest.pojo.Provider;
import com.kcs.rest.utils.AjaxMesg;
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

    @RequestMapping(value = "getProvider", produces = "text/html;charset=utf-8")
    public @ResponseBody
    String getPosition() {
        List<Provider> list = providerService.findAllProvider();
        JSONArray json = JSONArray.fromObject(list);
        String js = json.toString();
        System.out.println(js);
        return js;
    }

    @RequestMapping("/rAddProvider")
    public String rAddProvider() {
        return "addProvider";
    }

    @RequestMapping("/addProvider")
    @ResponseBody
    public AjaxMesg addProvider(String providerName, String providerAddress, String tel) {

        Provider provider=providerService.findProviderByName(providerName);
        if(provider==null){
            Integer integer = providerService.addProvider(providerName, providerAddress, tel);
            if (integer < 0)
                return new AjaxMesg(false, "新增供应商失败！");
            return new AjaxMesg(true, "新增供应商成功!");
        }
        else {
            return new AjaxMesg(false,"增加失败，供应商已存在！");
        }
    }
}
