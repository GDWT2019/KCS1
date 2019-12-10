package com.kcs.rest.controller;


import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Provider;
import com.kcs.rest.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("ProviderController")
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @RequestMapping(value="providerData")
    @ResponseBody
    public KcsResult UserData(){
        List<Provider> allProvider = providerService.findAllProvider();
        return KcsResult.ok(allProvider);
    }
}
