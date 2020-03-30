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

    @RequestMapping(value="findSumItemNumBygoodsIdAndInBillID")
    @ResponseBody
    public Integer findSumItemNumBygoodsIdAndInBillID(@RequestParam("goodsID") String goodsID, @RequestParam("inBillID") String inBillID){
        int gid = Integer.parseInt(goodsID);
        Integer sumItemNum = itemInService.findSumItemNumBygoodsIdAndInBillID(gid, inBillID);
        return sumItemNum;
    }

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

    @RequestMapping("/delItemByInBillIDandGoodsID")
    @ResponseBody
    public void delItemByInBillIDandGoodsID(@RequestParam("goodsID") String goodsID, @RequestParam("inBillID") String inBillID){
        int InBillID = Integer.parseInt(inBillID);
        int GoodsID = Integer.parseInt(goodsID);
        itemInService.delItemByInBillIDandGoodsID(InBillID,GoodsID);
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

    @RequestMapping("/findItemsInByItemsID{itemsInID}")
    @ResponseBody
    public KcsResult findItemsInByItemsID(@PathVariable int itemsInID){
        ItemIn itemIn = itemInService.findItemsInByItemsID(itemsInID);
        if (itemIn != null) {
            return KcsResult.ok(itemIn);
        } else
            return KcsResult.build(500, "根据入库ID，未找到对应物品");
    }

    //获取入库单数据
    @RequestMapping("/findItemsIdByInBillID{inBillID}")
    @ResponseBody
    public KcsResult findItemsIdByInBillID(@PathVariable int inBillID){
        List<ItemIn> itemInList = itemInService.findItemsIdByInBillID(inBillID);
        return KcsResult.ok(itemInList);
    }

}
