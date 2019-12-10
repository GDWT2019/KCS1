package com.kcs.portal.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcs.portal.service.*;
import com.kcs.rest.pojo.*;
import com.kcs.rest.utils.AjaxMesg;
import com.kcs.rest.utils.GetTime;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller("addOutBillController")
@RequestMapping("/outBill")
public class OutBillController {

    @Autowired
    private OutBillService outBillService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SummaryService summaryService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemsOutService itemsOutService;


    //跳转到outBillData页面
    @RequestMapping("/showAllOutBill")
    public String showAllOutBill(){
        return "outBillData";
    }

    //获取所有出库表数据
    @RequestMapping(value = "/getAllOutBill",produces="text/html;charset=utf-8")
    @ResponseBody
    public String allOutBill(){
        List<OutBillPresent> allOutBillPresent = outBillService.getAllOutBillPresent();
        JSONArray json = JSONArray.fromObject(allOutBillPresent);
        String js=json.toString();
        String outBillJson = "{\"code\":0,\"msg\":\"\",\"count\":"+allOutBillPresent.size()+",\"data\":"+js+"}";
        return outBillJson;
    }

    //跳转到addOutBill页面
    @RequestMapping("/showAddOutBill")
    public String showAddOutBill(Model model){
        //查找汇总表的最新记录的所有唯一goodsID，根据这些id查询物品表，并返回按名称分组后物品名称
        List<Goods> goodsList = goodsService.findItemsNameUnique();

        //查找最大的OutBillID，+1后变成编号
        Integer maxOutBillID = outBillService.findTheMaxOutBillID();

        //查找部门信息
        List<Department> departmentList = outBillService.getAllDepartment();

        //查找所有用户
        List<User> userList = userService.findAllUser();

        model.addAttribute("goodsList",goodsList);
        model.addAttribute("outBillID",maxOutBillID+1);
        model.addAttribute("departmentList",departmentList);
        model.addAttribute("userList",userList);
        return "addOutBill";
    }

    @RequestMapping("/addOutBill")
    public String AddOutBill(){
        return "addOutBill1";
    }

    @RequestMapping("/findGoodsByItemsName")
    @ResponseBody
    public List<Goods> findGoodsByItemName(String itemsName) {
        List<Goods> goodsList = goodsService.findGoodsByItemsName(itemsName);
        return goodsList;
    }

    @RequestMapping("/findSummaryByItemsNameAndItemsType")
    @ResponseBody
    public Summary findSummaryByItemsNameAndItemsType(Goods g) {

        Goods goods = goodsService.findGoodsByItemsNameAndItemsType(g);     //根据品名和规格查找物品


        //根据物品id，在在所有最新物品的记录中查找该物品对应的汇总
        Summary thisSummary = summaryService.findSummaryInTheLastGoodsDataByGoodsID(goods.getGoodsID());

        return thisSummary;
    }

    @RequestMapping("/insertOutBill")
    @ResponseBody
    public AjaxMesg insertBill(HttpSession session,String itemsOutJsonListString, String outBillString) throws IOException {
        AjaxMesg ajaxMesg = new AjaxMesg(true,"恭喜，插入成功了！");

        ObjectMapper mapper = new ObjectMapper();
        List<ItemsOut> itemsOutList = mapper.readValue(itemsOutJsonListString,new TypeReference<List<ItemsOut>>() { });

        JSONObject jsonobject = JSONObject.fromObject(outBillString);
        OutBill outBill= (OutBill)JSONObject.toBean(jsonobject,OutBill.class);

        //把存在session里的操作人，操作时间插入该outBill
        User user = (User)session.getAttribute("user");
        if(user == null){
            return new AjaxMesg(false,"请先登录！");
        }

        //若当前已登录，则插入操作人id和操作时间
        outBill.setOperator(user.getUserID());
        outBill.setOperateTime(GetTime.getTime());

        //插入出库表，并返回该条数据的id
        Integer outBillID = outBillService.insertOutBill(outBill);

        //把每一条出库物品都插入出库id
        if (outBillID == null){
            return new AjaxMesg(false,"出库单插入失败！");
        }

        //TODO  把itemsOutList传到后台
        for (ItemsOut out : itemsOutList) {
            out.setOutBillID(outBillID);
            Integer i = itemsOutService.insertItemsOut(out);
            if (i<1){
                return new AjaxMesg(false,"出库物品插入失败！");
            }
        }

        return ajaxMesg;
    }

    //获取所有出库表数据
    @RequestMapping(value = "/findOutBillPresentByOutBillID",produces="text/html;charset=utf-8")
    @ResponseBody
    public String findOutBillPresentByOutBillID(int outBillID){
        List<OutBillPresent> allOutBillPresent = outBillService.findOutBillPresentByOutBillID(outBillID);
        JSONArray json = JSONArray.fromObject(allOutBillPresent);
        String js=json.toString();
        String outBillJson = "{\"code\":0,\"msg\":\"\",\"count\":"+allOutBillPresent.size()+",\"data\":"+js+"}";
        return outBillJson;
    }
}
