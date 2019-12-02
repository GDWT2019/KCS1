package com.kcs.portal.controller;

import com.kcs.portal.service.GoodsService;
import com.kcs.portal.service.OutBillService;
import com.kcs.rest.pojo.Department;
import com.kcs.rest.pojo.Goods;
import com.kcs.rest.pojo.OutBillPresent;
import com.kcs.rest.pojo.Summary;
import net.sf.json.JSONArray;
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
    private OutBillService outBillService;
    @Autowired
    private GoodsService goodsService;

    //跳转到outBillData页面
    @RequestMapping("/showAllOutBill")
    public String showAllOutBill(){
        return "outBillData";
    }

    //获取所有出库表数据
    @RequestMapping("/getAllOutBill")
    @ResponseBody
    public String allOutBill(){
        List<OutBillPresent> allOutBillPresent = outBillService.getAllOutBillPresent();
        JSONArray json = JSONArray.fromObject(allOutBillPresent);
        String js=json.toString();
        String outBillJson = "{\"code\":0,\"msg\":\"\",\"count\":"+allOutBillPresent.size()+",\"data\":"+js+"}";
        System.out.println(outBillJson);
        return outBillJson;
    }

    //跳转到addOutBill页面
    @RequestMapping("/showAddOutBill")
    public String showAddOutBill(){
        return "addOutBill";
    }

    @RequestMapping("/addOutBill")
    public String AddOutBill(Model model){
        List<Goods> goodsList = outBillService.getAllGoodsInSummaryGoodsId();
        List<Summary> summaryList = outBillService.getAllSummary();
        List<Department> departmentList = outBillService.getAllDepartment();

        model.addAttribute("goodsList",goodsList);
        model.addAttribute("summaryList",summaryList);
        model.addAttribute("departmentList",departmentList);
        return "addOutBill1";
    }

    @RequestMapping("/findGoodsByItemsName")
    @ResponseBody
    public List<Goods> findGoodsByItemName(String itemsName) {
        List<Goods> goodsList = goodsService.findGoodsByItemName(itemsName);
        return goodsList;
    }
}
