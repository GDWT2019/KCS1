package com.kcs.rest.controller;

import com.kcs.rest.pojo.*;
import com.kcs.rest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("outBillController")
@RequestMapping("/outBill")
public class OutBillController {

    @Autowired
    private SummaryService summaryService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private OutBillPresentService outBillPresentService;
    @Autowired
    private OutBillService outBillService;

    //获取出库记录分页显示数据
    @RequestMapping(value="ItemOutRecord",method=RequestMethod.GET)
    @ResponseBody
    public KcsResult ItemOutRecord(@RequestParam("before")String before,@RequestParam("after")String after,@RequestParam("goodsID")String goodsID){

        int front = Integer.parseInt(before);
        int back = Integer.parseInt(after);
        int id = Integer.parseInt(goodsID);

        List<OutBillPresent> allInBill = outBillService.ItemOutRecord(front,back,id);

        return KcsResult.ok(allInBill);
    }

    //出库记录数量
    @RequestMapping("/CountItemOutRecord{goodsid}")
    @ResponseBody
    public  KcsResult CountItemOutRecord(@PathVariable int goodsid){
        int count = outBillService.CountItemOutRecord(goodsid);
        return KcsResult.ok(count);
    }

    @RequestMapping("/getGoodsById{GoodsID}")
    @ResponseBody
    public KcsResult getGoodsById(@PathVariable int GoodsID) {
        Goods goods = goodsService.findGoodsById(GoodsID);
        if (goods != null) {
            return KcsResult.ok(goods);
        } else
            return KcsResult.build(500, "根据GoodsID，未找到对应物品");
    }

    /**
     * 根据Summary中GoodsID，联合查询Goods字段，根据ItemsName分组
     *
     * @return
     */
    @RequestMapping("/getAllGoodsInSummaryGoodsId")
    @ResponseBody
    public KcsResult getAllGoodsInSummaryGoodsId() {
        List<Goods> goodsList = goodsService.findGoodsInSummaryGoodsID();
        if (goodsList != null) {
            return KcsResult.ok(goodsList);
        } else
            return KcsResult.build(500, "未找到对应物品");
    }


    @RequestMapping("/getAllSummary")
    @ResponseBody
    public KcsResult getAllSummary() {
        List<Summary> summaryList = summaryService.findAllSummary();
        if (summaryList != null) {
            return KcsResult.ok(summaryList);
        } else
            return KcsResult.build(500, "未找到汇总表数据");
    }

    @RequestMapping("/getAllDepartment")
    @ResponseBody
    public KcsResult getAllDepartment() {
        List<Department> departmentList = departmentService.findAllDepartment();
        if (departmentList != null) {
            return KcsResult.ok(departmentList);
        } else
            return KcsResult.build(500, "未找到部门表数据");
    }

    @RequestMapping(value="/outBillPresent",method=RequestMethod.GET)
    @ResponseBody
    public KcsResult outBillPresent(@RequestParam("begin")String begin,@RequestParam("end")String end,@RequestParam("time1")String time1,@RequestParam("time2")String time2,@RequestParam("itemName")String itemName,@RequestParam("checkStatus")String checkStatus,@RequestParam("userID")String userID ){
        summaryService.updateSummaryToNewMonth();
        int front = Integer.parseInt(begin);
        int back = Integer.parseInt(end);
        int status = Integer.parseInt(checkStatus);
        int uid = Integer.parseInt(userID);
        List<OutBillPresent> allOutBillPresent = outBillPresentService.findAllOutBillPresent(front,back,time1,time2,itemName,status,uid);
        if (allOutBillPresent != null) {
            return KcsResult.ok(allOutBillPresent);
        } else
            return KcsResult.build(500, "未找到出库任何数据");
    }

    @RequestMapping(value="/outBillPresentCount",method=RequestMethod.GET)
    @ResponseBody
    public KcsResult outBillPresentCount(@RequestParam("time1")String time1,@RequestParam("time2")String time2,@RequestParam("itemName")String itemName,@RequestParam("checkStatus")String checkStatus,@RequestParam("userID") String userID){
        int status = Integer.parseInt(checkStatus);
        int uid = Integer.parseInt(userID);
        Integer count = outBillPresentService.outBillPresentCount(time1,time2,itemName,status,uid);
        if (count != null) {
            return KcsResult.ok(count);
        } else
            return KcsResult.build(500, "未找到出库任何数据");
    }

    @RequestMapping("/insertOutBill")
    @ResponseBody
    public KcsResult updatePass(@RequestBody OutBill outBill) {
        Integer i = outBillService.insertOutBill(outBill);
        return KcsResult.ok(i);
    }

    @RequestMapping("/getOutBillPresentByOutBillID{outBillID}")
    @ResponseBody
    public KcsResult getOutBillPresentByOutBillID(@PathVariable int outBillID){
        List<OutBillPresent> outBillPresentList = outBillPresentService.findOutBillPresentByOutBillID(outBillID);
        if (outBillPresentList != null){
            return KcsResult.ok(outBillPresentList);
        }else
            return KcsResult.build(500, "查不到数据！");
    }

    @RequestMapping("/getTheMaxOutBillID")
    @ResponseBody
    public KcsResult getTheMaxOutBillID(){
        Integer theMaxOutBillID = outBillService.findTheMaxOutBillID();
        if (theMaxOutBillID != null){
            return KcsResult.ok(theMaxOutBillID);
        }else
            return KcsResult.build(500, "查不到数据！");
    }

    @RequestMapping("/updateCheckByOutBillID")
    @ResponseBody
    public KcsResult updateCheckByOutBillID(@RequestBody OutBill outBill){
        Integer i = outBillService.updateCheckByOutBillID(outBill);
        if (i != null){
            return KcsResult.ok(i);
        }else
            return KcsResult.build(500, "更新失败！");
    }

    @RequestMapping("/updateOutBill")
    @ResponseBody
    public KcsResult updateOutBill(@RequestBody OutBill outBill){
        Integer i = outBillService.updateOutBill(outBill);
        if (i != null){
            return KcsResult.ok(i);
        }else
            return KcsResult.build(500, "更新失败！");
    }

    //获取入库显示数据
    @RequestMapping("/outBillPresentPrint")
    @ResponseBody
    public KcsResult outBillPresentPrint(){
        List<OutBillPresent> outBillPresents = outBillPresentService.findAllOutBillPresentPrint();
        return KcsResult.ok(outBillPresents);
    }
}
