package com.kcs.rest.controller;

import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.inBillShow;
import com.kcs.rest.service.InBillService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("inBillController")
@RequestMapping("/inBill")
public class InBillController {

    @Autowired
    private InBillService inBillService;


    @RequestMapping("/findCheckMessageByID{inBillID}")
    @ResponseBody
    public KcsResult findCheckMessageByID(@PathVariable int inBillID){
        InBill inBill = inBillService.findCheckMessageByID(inBillID);
        if (inBill != null) {
            return KcsResult.ok(inBill);
        } else
            return KcsResult.build(500, "根据物品ID，未找到对应物品");
    }

    //查找数据库中的最大id值
    @RequestMapping("/findMaxInBillID")
    @ResponseBody
    public KcsResult findMaxInBillID(){
        int theMaxinBillID = inBillService.findMaxInBillID();
        System.out.println("restcontroller:"+theMaxinBillID);
            return KcsResult.ok(theMaxinBillID);

    }

    //获取入库单数据
    @RequestMapping(value="inBillData")
    @ResponseBody
    public KcsResult UserData(){
        List<InBill> allInBill = inBillService.findAllInBill();
        return KcsResult.ok(allInBill);
    }

    //入库单的数据
    @RequestMapping(value = "inBillTotal")
    @ResponseBody
    public  KcsResult InBillTotal(){
        int count = inBillService.count();
        return KcsResult.ok(count);
    }
    //入库显示的数据
    @RequestMapping(value = "inBillShowTotal")
    @ResponseBody
    public  KcsResult inBillShowTotal(){
        int count = inBillService.countShow();
        return KcsResult.ok(count);
    }

    //获取自增的id值
    @RequestMapping(value = "insertNewBill" ,method = RequestMethod.POST)
    @ResponseBody
    public  KcsResult insertNewBill(@RequestBody InBill inBill){
        System.out.println("InBill:   "+inBill);
        inBillService.insertNewBill(inBill);
        Integer inBillID = inBill.getInBillID();
        System.out.println("controller  " +inBillID);
        return KcsResult.ok(inBillID);

    }

    //获取自增的id值
    @RequestMapping(value = "UpdateInBill" ,method = RequestMethod.POST)
    @ResponseBody
    public  void UpdateInBill(@RequestBody InBill inBill){

        inBillService.UpdateInBill(inBill);

    }

    //获取入库显示数据
    @RequestMapping(value="inBillShowData")
    @ResponseBody
    public KcsResult inBillShowData(){
        List<inBillShow> allInBill = inBillService.inBillShowData();
        return KcsResult.ok(allInBill);
    }

    //获取入库分页显示数据
    @RequestMapping(value="inBillShowPage",method=RequestMethod.GET)
    @ResponseBody
    public KcsResult inBillShowPage(@RequestParam("before")String before, @RequestParam("after")String after, @RequestParam("time1")String time1, @RequestParam("time2")String time2, @RequestParam("itemName")String itemName, @RequestParam("checkStatus") String status){

        int front = Integer.parseInt(before);
        int back = Integer.parseInt(after);
        Integer checkStatus = null;
        if("null".equals(status)){
            checkStatus = null;
        }
        else{
            checkStatus =Integer.parseInt(status);
        }

        List<inBillShow> allInBill = inBillService.inBillShowPage(front,back,time1,time2,itemName,checkStatus);
        System.out.println("allInBill="+allInBill);

        return KcsResult.ok(allInBill);
    }
    //入库显示的数据
    @RequestMapping(value = "countReload",method=RequestMethod.GET)
    @ResponseBody
    public  KcsResult countReload(@RequestParam("time1")String time1,@RequestParam("time2")String time2,@RequestParam("itemName")String itemName,@RequestParam("checkStatus") String status){
        Integer checkStatus = null;
        if("null".equals(status)){
            checkStatus = null;
        }else
        {
            checkStatus =Integer.parseInt(status);
        }
        int count = inBillService.countReload(time1,time2,itemName,checkStatus);
        return KcsResult.ok(count);
    }

    //获取入库记录分页显示数据
    @RequestMapping(value="ItemInRecord",method=RequestMethod.GET)
    @ResponseBody
    public KcsResult ItemInRecord(@RequestParam("before")String before,@RequestParam("after")String after,@RequestParam("goodsID")String goodsID){

        int front = Integer.parseInt(before);
        int back = Integer.parseInt(after);
        int id = Integer.parseInt(goodsID);

        List<inBillShow> allInBill = inBillService.ItemInRecord(front,back,id);

        return KcsResult.ok(allInBill);
    }

    //入库记录数量
    @RequestMapping("/CountItemInRecord{goodsid}")
    @ResponseBody
    public  KcsResult CountItemInRecord(@PathVariable int goodsid){
        int count = inBillService.CountItemInRecord(goodsid);
        return KcsResult.ok(count);
    }

}
