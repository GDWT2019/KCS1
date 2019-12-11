package com.kcs.rest.controller;

import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Summary;
import com.kcs.rest.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("summaryController")
@RequestMapping("/summary")
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

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
}

