package com.kcs.portal.controller;


import com.kcs.portal.service.ProviderService;
import com.kcs.rest.pojo.Provider;
import com.kcs.rest.utils.AjaxMesg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("providerController")
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @RequestMapping("/delProvider")
    @ResponseBody
//    @PreAuthorize("hasAnyAuthority('角色管理,角色修改,ROLE_ADMIN')")
    public AjaxMesg delProvider(Provider provider){

        int i = providerService.delProvider(provider);

        if (i<0)
            return new AjaxMesg(false,"删除失败");

        return new AjaxMesg(true,"删除成功！");

    }

    @RequestMapping("/updateProvider")
    @ResponseBody
//    @PreAuthorize("hasAnyAuthority('角色管理,角色修改,ROLE_ADMIN')")
    public AjaxMesg updateProvider(Provider provider){
        Provider provider1=providerService.findOtherProvider(provider.getProviderID(),provider.getProviderName());
        if(provider1==null) {
            int i = providerService.updateProvider(provider);
            if (i < 0)
                return new AjaxMesg(false, "修改失败");

            return new AjaxMesg(true, "修改成功！");
        }else {
            return new AjaxMesg(false,"修改失败，供应商已存在！");
        }

    }

    @RequestMapping(value = "/showUpdateProviderByID",method= RequestMethod.POST,produces ="text/html;charset=utf-8")
    @ResponseBody
    public String showUpdateProviderByID(int providerID){
        Provider provider = providerService.showUpdateProviderByID(providerID);
        JSONArray json = JSONArray.fromObject(provider);
        String js=json.toString();
        return js;
    }

    @RequestMapping("/showUpdateProvider")
    public String showUpdateProvider() {
        return "updateProvider";
    }


    @RequestMapping(value = "/providerData",produces="text/html;charset=utf-8")
    @ResponseBody
    public String providerData(HttpServletRequest request){
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        int before=limit*(page-1)+1;
        int after = page * limit;
        List<Provider> providerDataList = providerService.providerData(before,after);
        int count = providerService.countProviderData();
        JSONArray json = JSONArray.fromObject(providerDataList);
        String js=json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        return jso;
    }

    @RequestMapping("/showProviderData")
    public String showProviderData() {
        return "providerData";
    }

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
