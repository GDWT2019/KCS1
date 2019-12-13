package com.kcs.portal.controller;

import com.kcs.portal.service.ItemInService;
import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.ItemsShow;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    //跳转审批页面
    @RequestMapping("/checkUpdate")
    public String checkUpdate(){
        return "checkUpdate";
    }
    //跳转审批页面
    @RequestMapping("/updateInBill")
    public String updateInBill(){
        return "updateInBill";
    }

    //修改审批状态
    @RequestMapping("/UpdateCheckStatus")
    @ResponseBody
    public void UpdateCheckStatus(HttpServletRequest request){
        String chcckTime = request.getParameter("chcckTime");
        String checkStatus = request.getParameter("checkStatus");
        String inBillID = request.getParameter("InBillID");
        String checkMessage = request.getParameter("checkMessage");
        String checker = request.getParameter("checker");
        int checkerID = Integer.parseInt(checker);
        int status = Integer.parseInt(checkStatus);
        int id = Integer.parseInt(inBillID);
        InBill inBill =new InBill();
        inBill.setInBillID(id);
        inBill.setCheckStatus(status);
        inBill.setCheckMessage(checkMessage);
        inBill.setCheckTime(chcckTime);
        inBill.setChecker(checkerID);
        System.out.println(inBill);
       itemInService.UpdateCheckStatus(inBill);
    }

    @RequestMapping("/valueIDandTime")
    @ResponseBody
    public List<InBill> valueIDandTime(HttpServletRequest request){
        String inBillID = request.getParameter("InBillID");
        List<InBill> list =itemInService.valueIDandTime(inBillID);
        System.out.println("portal controller:" +list);
        return list;
    }
}
