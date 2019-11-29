package com.kcs.portal.controller;

import com.kcs.portal.service.GoodsService;
import com.kcs.portal.service.OutBillService;
import com.kcs.rest.pojo.Department;
import com.kcs.rest.pojo.Goods;
import com.kcs.rest.pojo.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("addOutBillController")
@RequestMapping("/outBill")
public class OutBillController {

    @Autowired
    private OutBillService addOutBillService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/addOutBill")
    public String AddOutBill(Model model){
        List<Goods> goodsList = addOutBillService.getAllGoodsInSummaryGoodsId();
        List<Summary> summaryList = addOutBillService.getAllSummary();
        List<Department> departmentList = addOutBillService.getAllDepartment();

        model.addAttribute("goodsList",goodsList);
        model.addAttribute("summaryList",summaryList);
        model.addAttribute("departmentList",departmentList);
        return "addOutBill";
    }

    @RequestMapping("/findGoodsByItemsName")
    @ResponseBody
    public List<Goods> findGoodsByItemName(String itemsName) {
        List<Goods> goodsList = goodsService.findGoodsByItemName(itemsName);
        for (Goods g :
                goodsList) {
            System.out.println(g);
        }
        return goodsList;
    }
}
