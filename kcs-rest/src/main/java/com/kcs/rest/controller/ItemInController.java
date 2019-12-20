package com.kcs.rest.controller;

import com.kcs.rest.pojo.*;
import com.kcs.rest.service.ItemInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("itemInController")
@RequestMapping("/itemIn")
public class ItemInController {

    @Autowired
    private ItemInService itemInService;

    //获取自增的id值
    @RequestMapping(value = "insertNewItem" ,method = RequestMethod.POST)
    @ResponseBody
    public  void insertNewItem(@RequestBody ItemIn itemIn){
        System.out.println("ItemIn:   "+itemIn);
        itemInService.insertNewItem(itemIn);
    }

    //获取自增的id值
    @RequestMapping( "/findItemsInData{InBillID}")
    @ResponseBody
    public KcsResult findItemsInData(@PathVariable String InBillID){

        int inBillID = Integer.parseInt(InBillID);

        List<ItemsShow> allItensShow = itemInService.findItemsInData(inBillID);

        return KcsResult.ok(allItensShow);

    }

    @RequestMapping("/delItem{itemsInID}")
    @ResponseBody
    public void delItem(@PathVariable String itemsInID){
        int ItemsInID = Integer.parseInt(itemsInID);
        itemInService.delItem(ItemsInID);
    }

    @RequestMapping("/delItemByInBillID{inBillID}")
    @ResponseBody
    public void delItemByInBillID(@PathVariable String inBillID){
        int InBillID = Integer.parseInt(inBillID);
        itemInService.delItemByInBillID(InBillID);
    }

    @RequestMapping(value = "UpdateCheckStatus",method = RequestMethod.POST)
    @ResponseBody
    public void UpdateCheckStatus(@RequestBody InBill inBill){
        System.out.println("UpdateCheckStatus:" + inBill);
        itemInService.UpdateCheckStatus(inBill);
    }

    @RequestMapping("/valueIDandTime{inBillID}")
    @ResponseBody
    public KcsResult valueIDandTime(@PathVariable String inBillID){
        int BillID = Integer.parseInt(inBillID);
        List<InBill> inBill=itemInService.valueIDandTime(BillID);
        return KcsResult.ok(inBill);
    }

    @RequestMapping("/getItemsInList{inBillID}")
    @ResponseBody
    public KcsResult getItemsInList(@PathVariable String inBillID){
        int BillID = Integer.parseInt(inBillID);
        List<GoodsAndCategoryAndItemsIn> ItemInList=itemInService.getItemsInList(BillID);
        return KcsResult.ok(ItemInList);
    }

}
