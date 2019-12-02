package com.kcs.rest.controller;

import com.kcs.rest.pojo.*;
import com.kcs.rest.service.DepartmentService;
import com.kcs.rest.service.GoodsService;
import com.kcs.rest.service.OutBillPresentService;
import com.kcs.rest.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
     *  根据Summary中GoodsID，联合查询Goods字段，根据ItemsName分组
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

    @RequestMapping("/outBillPresent")
    @ResponseBody
    public KcsResult outBillPresent() {
        List<OutBillPresent> allOutBillPresent = outBillPresentService.findAllOutBillPresent();
        if (allOutBillPresent != null) {
            return KcsResult.ok(allOutBillPresent);
        } else
            return KcsResult.build(500, "未找到出库任何数据");
    }
}
