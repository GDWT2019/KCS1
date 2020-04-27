package com.kcs.rest.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.SummartAndGoodsAndCategory;
import com.kcs.rest.pojo.Summary;
import com.kcs.rest.service.SummaryService;
import com.kcs.rest.utils.FileUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Controller("summaryController")
@RequestMapping("/summary")
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @RequestMapping(value="findBetweenBeforeAndAffterInAmout")
    @ResponseBody
    public Integer findBetweenBeforeAndAffterInAmout(@RequestParam("goodsID") String goodsID, @RequestParam("subTime") String subTime,@RequestParam("subTime1") String subTime1){
        int gid = Integer.parseInt(goodsID);
        Integer beforeAndAffterInAmout = summaryService.findBetweenBeforeAndAffterInAmout(gid, subTime,subTime1);
        return beforeAndAffterInAmout;
    }

    @RequestMapping(value="findBetweenBeforeAndAffterOutAmout")
    @ResponseBody
    public Integer findBetweenBeforeAndAffterOutAmout(@RequestParam("goodsID") String goodsID, @RequestParam("subTime") String subTime,@RequestParam("subTime1") String subTime1){
        int gid = Integer.parseInt(goodsID);
        Integer beforeAndAffterOutAmout = summaryService.findBetweenBeforeAndAffterOutAmout(gid, subTime,subTime1);
        return beforeAndAffterOutAmout;
    }



    @RequestMapping(value="findLongestAfterSummary")
    @ResponseBody
    public KcsResult findLongestAfterSummary(@RequestParam("goodsID") String goodsID, @RequestParam("subTime") String subTime){
        int gid = Integer.parseInt(goodsID);
        Summary longestAfterSummary = summaryService.findLongestAfterSummary(gid, subTime);
        return KcsResult.ok(longestAfterSummary);
    }

    @RequestMapping(value="findlatestAfterSummary")
    @ResponseBody
    public KcsResult findlatestAfterSummary(@RequestParam("goodsID") String goodsID, @RequestParam("subTime") String subTime){
        int gid = Integer.parseInt(goodsID);
        Summary latestAfterSummary = summaryService.findlatestAfterSummary(gid, subTime);
        return KcsResult.ok(latestAfterSummary);
    }

    @RequestMapping(value="findAllafterInAmout")
    @ResponseBody
    public Integer findAllafterInAmout(@RequestParam("goodsID") String goodsID, @RequestParam("subTime") String subTime){
        int gid = Integer.parseInt(goodsID);
        Integer allafterInAmout = summaryService.findAllafterInAmout(gid, subTime);
        return allafterInAmout;
    }

    @RequestMapping(value="findAllafterOutAmout")
    @ResponseBody
    public Integer findAllafterOutAmout(@RequestParam("goodsID") String goodsID, @RequestParam("subTime") String subTime){
        int gid = Integer.parseInt(goodsID);
        Integer allafterOutAmout = summaryService.findAllafterOutAmout(gid, subTime);
        return allafterOutAmout;
    }

    @RequestMapping(value="findAllBeforeInAmout")
    @ResponseBody
    public Integer findAllBeforeInAmout(@RequestParam("goodsID") String goodsID, @RequestParam("subTime") String subTime){
        int gid = Integer.parseInt(goodsID);
        Integer allBeforeInAmout = summaryService.findAllBeforeInAmout(gid, subTime);
        return allBeforeInAmout;
    }

    @RequestMapping(value="findAllBeforeOutAmout")
    @ResponseBody
    public Integer findAllBeforeOutAmout(@RequestParam("goodsID") String goodsID, @RequestParam("subTime") String subTime){
        int gid = Integer.parseInt(goodsID);
        Integer allBeforeInAmout = summaryService.findAllBeforeOutAmout(gid, subTime);
        return allBeforeInAmout;
    }

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
    public KcsResult summartyAllData(HttpServletResponse response){


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
    public KcsResult summaryAllCurrentdata(@RequestParam("before")String before,@RequestParam("after")String after,@RequestParam("time1")String time1, @RequestParam("time2")String time2,@RequestParam("itemName")String itemName){

        int front = Integer.parseInt(before);
        int back = Integer.parseInt(after);

        List<SummartAndGoodsAndCategory> summaryAllCurrentdata = summaryService.summaryAllCurrentdata(front,back,time1,time2,itemName);
        System.out.println("summaryAllCurrentdata="+summaryAllCurrentdata);

        return KcsResult.ok(summaryAllCurrentdata);
    }


    //重载后数据的数量
    @RequestMapping(value="countReload")
    @ResponseBody
    public  KcsResult countReload(@RequestParam("time1")String time1, @RequestParam("time2")String time2,@RequestParam("itemName")String itemName){
        int countReload = summaryService.countReload(time1,time2,itemName);
        return KcsResult.ok(countReload);
    }

    //某时某物品的剩余数量
    @RequestMapping(value="getThisTotal")
    @ResponseBody
   public KcsResult getThisTotal(@RequestParam("goodsID") String goodsID, @RequestParam("time") String time){
        int gid = Integer.parseInt(goodsID);
        int thisTotal = summaryService.getThisAmount(gid, time);
        return KcsResult.ok(thisTotal);
    }

    @RequestMapping("/poiSummary")
    public void  poiSummary(HttpServletResponse response){
        List<SummartAndGoodsAndCategory> list=summaryService.summartyAllData();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("汇总表","汇总"),SummartAndGoodsAndCategory .class, list);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("汇总.xls", "UTF-8"));
            response.setHeader("Pragma", "No-cache");//设置不要缓存
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value="summaryTotalByMonth{time}")
    @ResponseBody
    public Double summaryTotalByMonth(@PathVariable String time){
        Double motthTotal = summaryService.summaryTotalByMonth(time);
        return motthTotal;
    }
}

