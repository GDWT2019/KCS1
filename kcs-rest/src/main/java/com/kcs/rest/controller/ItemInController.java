package com.kcs.rest.controller;

import com.kcs.rest.pojo.ItemIn;
import com.kcs.rest.pojo.ItemsShow;
import com.kcs.rest.pojo.KcsResult;
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

}
