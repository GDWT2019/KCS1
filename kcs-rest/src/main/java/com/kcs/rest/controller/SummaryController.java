package com.kcs.rest.controller;

import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.SummartAndGoodsAndCategory;
import com.kcs.rest.pojo.Summary;
import com.kcs.rest.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("summaryController")
@RequestMapping("/summary")
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @RequestMapping(value="findThisMonthInAmountByGoodsID")
    @ResponseBody
    public KcsResult findThisMonthInAmountByGoodsID(@RequestParam("goodsID") String goodsID, @RequestParam("subTime") String subTime){
        int gid = Integer.parseInt(goodsID);
        Summary ThisMonthInAmount = summaryService.findThisMonthInAmountByGoodsID(gid, subTime);
        return KcsResult.ok(ThisMonthInAmount);
    }

    @RequestMapping(value="findAllInAmout{goodsID}")
    @ResponseBody
    public Integer findAllInAmout(@PathVariable Integer goodsID){
        Integer allInAmout = summaryService.findAllInAmout(goodsID);
        return allInAmout;
    }

    @RequestMapping(value="findAllOutAmout{goodsID}")
    @ResponseBody
    public Integer findAllOutAmout(@PathVariable Integer goodsID){
        Integer allOutAmout = summaryService.findAllOutAmout(goodsID);
        return allOutAmout;
    }

    @RequestMapping("/getSummaryByGoodsIDAndTime")
    @ResponseBody
    public KcsResult getSummaryByGoodsIDAndTime(@RequestBody Summary s){
        Summary summary = summaryService.findSummaryByGoodsIDAndTime(s.getGoodsID(),s.getTime());
        if (summary != null) {
            return KcsResult.ok(summary);
        } else
            return KcsResult.build(500, "根据物品名和时间，未找到汇总记录");
    }

    @RequestMapping("/getGoodsIDByTime{Time}")
    @ResponseBody
    public KcsResult getGoodsIDByTime(@PathVariable String Time){
        List<Integer> GoodsIDList = summaryService.findGoodsIDByTime(Time);
        if (GoodsIDList != null) {
            return KcsResult.ok(GoodsIDList);
        } else
            return KcsResult.build(500, "根据时间，未找到汇总记录");
    }

    @RequestMapping("/getSummaryInTheLastGoodsDataByGoodsID{GoodsID}")
    @ResponseBody
    public KcsResult getSummaryInTheLastGoodsDataByGoodsID(@PathVariable int GoodsID){
        Summary summary = summaryService.findSummaryInTheLastGoodsDataByGoodsID(GoodsID);
        if (summary != null) {
            return KcsResult.ok(summary);
        } else
            return KcsResult.build(500, "根据时间，未找到汇总记录");
    }

    //获取时间数据
    @RequestMapping(value="findAllTime")
    @ResponseBody
    public  KcsResult findAllTime(){
        List<SummartAndGoodsAndCategory> allTime = summaryService.findAllTime();
        return KcsResult.ok(allTime);
    }

    //获取分页显示数据
    @RequestMapping(value="summartyBillData",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult summartyBillData(@RequestParam("before")String before,@RequestParam("after")String after,@RequestParam("time")String time){

        int front = Integer.parseInt(before);
        int back = Integer.parseInt(after);

        List<SummartAndGoodsAndCategory> summartyBillData = summaryService.summartyBillData(front,back,time);
        System.out.println("summartyBillData="+summartyBillData);

        return KcsResult.ok(summartyBillData);
    }

    //获取显示数据
    @RequestMapping(value="summaryTotal{Time}")
    @ResponseBody
    public int summaryTotal(@PathVariable String Time){
        int summaryTotal = summaryService.summaryTotal(Time);
        return summaryTotal;
    }

    //获取全部显示数据
    @RequestMapping(value="summartyAllData",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult summartyAllData(){


        List<SummartAndGoodsAndCategory> summartyAllData = summaryService.summartyAllData();

        return KcsResult.ok(summartyAllData);
    }


    @RequestMapping(value="summaryAllTotal")
    @ResponseBody
    public int summaryAllTotal(){
        int summaryAllTotal = summaryService.summaryAllTotal();
        return summaryAllTotal;
    }

    //获取分页显示数据
    @RequestMapping(value="summaryAllCurrentdata",method= RequestMethod.GET)
    @ResponseBody
    public KcsResult summaryAllCurrentdata(@RequestParam("before")String before,@RequestParam("after")String after,@RequestParam("itemName")String itemName){

        int front = Integer.parseInt(before);
        int back = Integer.parseInt(after);

        List<SummartAndGoodsAndCategory> summaryAllCurrentdata = summaryService.summaryAllCurrentdata(front,back,itemName);
        System.out.println("summaryAllCurrentdata="+summaryAllCurrentdata);

        return KcsResult.ok(summaryAllCurrentdata);
    }


    //重载后数据的数量
    @RequestMapping(value="countReload{itemName}")
    @ResponseBody
    public int countReload(@PathVariable String itemName){
        int countReload = summaryService.countReload(itemName);
        return countReload;
    }

    //某时某物品的剩余数量
    @RequestMapping(value="getThisTotal")
    @ResponseBody
   public KcsResult getThisTotal(@RequestParam("goodsID") String goodsID, @RequestParam("time") String time){
        int gid = Integer.parseInt(goodsID);
        int thisTotal = summaryService.getThisAmount(gid, time);
        return KcsResult.ok(thisTotal);
    }
}

