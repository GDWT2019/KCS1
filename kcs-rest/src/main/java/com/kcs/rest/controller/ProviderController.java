package com.kcs.rest.controller;


import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Provider;
import com.kcs.rest.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("ProviderController")
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @RequestMapping("/countProviderData")
    @ResponseBody
    public KcsResult countProviderData(){
        return KcsResult.ok(providerService.countProviderData());
    }


    @RequestMapping("/delProvider")
    @ResponseBody
    public KcsResult delProvider(@RequestBody Provider provider){
        return KcsResult.ok(providerService.delProvider(provider));
    }

    @RequestMapping("/updateProvider")
    @ResponseBody
    public KcsResult updateProvider(@RequestBody Provider provider){
        return KcsResult.ok(providerService.updateProvider(provider));
    }

    @RequestMapping("/showUpdateProviderByID{providerID}")
    @ResponseBody
    public KcsResult showUpdateProviderByID(@PathVariable int providerID){
        Provider provider = providerService.showUpdateProviderByID(providerID);
        return  KcsResult.ok(provider);
    }

    @RequestMapping(value = "/providerDataPage",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult providerDataPage(@RequestParam("front")String before, @RequestParam("back")String after ){
        int front = Integer.parseInt(before);
        int back = Integer.parseInt(after);
        return KcsResult.ok(providerService.providerDataPage(front,back));
    }

    @RequestMapping(value="providerData")
    @ResponseBody
    public KcsResult UserData(){
        List<Provider> allProvider = providerService.findAllProvider();
        return KcsResult.ok(allProvider);
    }

    @RequestMapping("/addProvider")
    @ResponseBody
    public KcsResult addProvider(@RequestParam("providerName") String providerName,@RequestParam("providerAddress") String providerAddress,@RequestParam("tel") String tel){
        System.out.println(providerAddress);
        Integer integer = providerService.addProvider(providerName, providerAddress, tel);
        return KcsResult.ok(integer);

    }

    @RequestMapping("/findProviderByName{providerName}")
    @ResponseBody
    public KcsResult findProviderByName(@PathVariable String providerName){
        Provider provider = providerService.findProviderByName(providerName);
        return KcsResult.ok(provider);
    }
}
