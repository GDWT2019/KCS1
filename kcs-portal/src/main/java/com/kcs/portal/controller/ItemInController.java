package com.kcs.portal.controller;

import com.kcs.portal.service.ItemInService;
import com.kcs.rest.pojo.ItemsShow;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("itemInController")
@RequestMapping("/itemIn")
public class ItemInController  {

    @Autowired
    private ItemInService itemInService;

    @RequestMapping(value = "itemsInData",produces = "text/html;charset=utf-8")
    public @ResponseBody String itemsInData(HttpServletRequest request) {
        String inBillID = request.getParameter("InBillID");
        List<ItemsShow> allItemsShow=itemInService.findItemsInData(inBillID);
        JSONArray json = JSONArray.fromObject(allItemsShow);
        String js=json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;
    }

    @RequestMapping("/delItem")
    @ResponseBody
    public void delItem(HttpServletRequest request){
        String itemsInID = request.getParameter("ItemsInID");
        itemInService.delItem(itemsInID);

    }
}
