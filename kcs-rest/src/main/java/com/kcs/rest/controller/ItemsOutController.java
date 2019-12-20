package com.kcs.rest.controller;

import com.alibaba.fastjson.JSON;
import com.kcs.rest.pojo.*;
import com.kcs.rest.service.*;
import com.kcs.rest.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("itemsOutController")
@RequestMapping("/itemsOut")
public class ItemsOutController {

    @Autowired
    private  ItemsOutService itemsOutService;
    @Autowired
    private SummaryService summaryService;
    @Autowired
    private ItemInService itemInService;

    @RequestMapping("/insertItemsOut")
    @ResponseBody
    public KcsResult insertItemsOut(@RequestBody ItemsOut itemsOut) {

        int i = itemsOutService.insertItemsOut(itemsOut);
        if (i > 0) {
            return KcsResult.ok(i);
        } else
            return KcsResult.build(500, "插入失败！");
    }

    @RequestMapping("/delItemsOut{itemsOutID}")
    @ResponseBody
    public KcsResult delItemsOut(@PathVariable int itemsOutID) {
        int i = itemsOutService.delItemsOutByID(itemsOutID);
        if (i > 0) {
            return KcsResult.ok(i);
        } else
            return KcsResult.build(500, "删除失败！");
    }

    @RequestMapping("/getItemsOutByOutBillID{outBillID}")
    @ResponseBody
    public KcsResult getItemsOutByOutBillID(@PathVariable int outBillID) {
        List<ItemsOut> itemsOutList = itemsOutService.findItemsOutByOutBillID(outBillID);
        if (itemsOutList != null) {
            return KcsResult.ok(itemsOutList);
        } else
            return KcsResult.build(500, "对应出库单无出库物品数据！");
    }

    @RequestMapping("/updateItemsOut")
    @ResponseBody
    public KcsResult updateItemsOut(@RequestBody ItemsOut itemsOut){
        Integer i = itemsOutService.updateItemsOut(itemsOut);
        if (i != null){
            return KcsResult.ok(i);
        }else
            return KcsResult.build(500, "更新失败！");
    }
}
