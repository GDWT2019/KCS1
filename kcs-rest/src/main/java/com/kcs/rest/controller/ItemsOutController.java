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

    @RequestMapping("/insertItemsOut")
    @ResponseBody
    public KcsResult insertItemsOut(@RequestBody ItemsOut itemsOut) {
        //插入出库物品信息
        int i = itemsOutService.insertItemsOut(itemsOut);
        if (i > 0) {
            //获取对应物品id的最新的汇总记录
            Summary summary = summaryService.findSummaryInTheLastGoodsDataByGoodsID(itemsOut.getGoodsID());

            //判断该记录出库信息
            if (summary.getOutAmount()>0||summary.getOutAmount()!=null){
                summary.setOutAmount(summary.getOutAmount()+itemsOut.getItemNum());
                summary.setOutTotal(summary.getOutTotal()+itemsOut.getItemTotal());
            }
            else{
                summary.setOutAmount(itemsOut.getItemNum());
                summary.setOutTotal(itemsOut.getItemTotal());
            }
            summary.setOutPrice(itemsOut.getItemPrice());
            summary.setThisAmount(summary.getThisAmount()-itemsOut.getItemNum());
            summary.setThisTotal(summary.getThisTotal()-itemsOut.getItemTotal());
            summaryService.updateSummary(summary);

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

}
