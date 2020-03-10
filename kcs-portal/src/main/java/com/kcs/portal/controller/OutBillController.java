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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    @Autowired
    private CategoryService categoryService;

    //跳转物品出库记录
    @RequestMapping("/rItemOutRecord")
    public String RItemOutRecord(HttpServletRequest request){

        return "ItemOutRecord";
    }

    //获取出库记录数据
    @RequestMapping(value="ItemOutRecord",produces="text/html;charset=utf-8")
    public @ResponseBody
    String ItemOutRecord(HttpServletRequest request){
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        int goodsID = Integer.parseInt(request.getParameter("goodsID"));


        int before=limit*(page-1)+1;
        int after = page * limit;

        List<OutBillPresent> list=outBillService.ItemOutRecord(before,after,goodsID);
        int count =outBillService.CountItemOutRecord(goodsID);

        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;
    }

    //跳转到outBillData页面
    @RequestMapping("/showAllOutBill")
    public String showAllOutBill(){
        return "outBillData";
    }

    //获取所有出库表数据
    @RequestMapping(value = "/getAllOutBill",produces="text/html;charset=utf-8")
    @ResponseBody
    public String allOutBill(HttpServletRequest request){

        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        int begin=limit*(page-1)+1;
        int end = page * limit;
        int checkStatus = 3;
        String checkStatus1 = request.getParameter("checkStatu");
        if (!checkStatus1.equals("")){
            checkStatus = Integer.parseInt(checkStatus1);
        }
        String time1 = request.getParameter("time1");
        String time2 = request.getParameter("time2");
        String itemName = request.getParameter("itemName");

        request.setAttribute("timeRange",time1+"-"+time2);
        request.setAttribute("itemName",itemName);
        request.setAttribute("checkStatu",checkStatus);

        List<OutBillPresent> allOutBillPresent = outBillService.getAllOutBillPresent(begin,end,time1,time2,itemName,checkStatus);
        Integer count = outBillService.outBillPresentCount(time1,time2,itemName,checkStatus);

        JSONArray json = JSONArray.fromObject(allOutBillPresent);
        String js=json.toString();
        if (allOutBillPresent==null){
            count = 0;
            js="[]";
        }
        String outBillJson = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        System.out.println(outBillJson);
        return outBillJson;
    }

    //跳转到addOutBill页面
    @RequestMapping("/showAddOutBill")
    public String showAddOutBill(Model model){

        //查找所有类型
        List<Category> categoryList = categoryService.findAllCategory();

        //查找最大的OutBillID，+1后变成编号
        Integer maxOutBillID = outBillService.findTheMaxOutBillID();

        //查找部门信息
        List<Department> departmentList = outBillService.getAllDepartment();

        //查找所有用户
        List<User> userList = userService.findAllUser();

        //查找制表人
        List<User> alllister = userService.findAlllister();

        //查找仓管员
        List<User> allWarehouse = userService.findAllWarehouse();

        model.addAttribute("categoryList",categoryList);
        model.addAttribute("outBillID",maxOutBillID+1);
        model.addAttribute("departmentList",departmentList);
        model.addAttribute("userList",userList);
        model.addAttribute("alllister",alllister);
        model.addAttribute("allWarehouse",allWarehouse);
        return "addOutBill";
    }

    @RequestMapping("/addOutBill")
    public String AddOutBill(){
        return "addOutBill";
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

    @RequestMapping(value = "/showUpdateOutBill",method= RequestMethod.GET)
    public String showUpdateOutBill(HttpServletRequest request, Model model){
        int outBillID =Integer.valueOf(request.getParameter("outBillID"));
        List<OutBillPresent> outBillPresentList = outBillService.findOutBillPresentByOutBillID(outBillID);

        //查找分类
        List<Category> categoryList = categoryService.findAllCategory();

        //查找部门信息
        List<Department> departmentList = outBillService.getAllDepartment();

        //查找所有用户
        List<User> userList = userService.findAllUser();


        model.addAttribute("categoryList",categoryList);
        model.addAttribute("departmentList",departmentList);
        model.addAttribute("userList",userList);
        model.addAttribute("outBillPresentList",outBillPresentList);
        return "updateOutBill";
    }

    @RequestMapping("/findTheLastItemsNameByCategoryName")
    @ResponseBody
    public List<String> findTheLastItemsNameByCategoryName(String categoryName){
       return goodsService.findTheLastItemsNameByCategoryName(categoryName);
    }

    @RequestMapping("/findAddOutBillByItemsName")
    @ResponseBody
    public List<AddOutBill> findAddOutBillByItemsName(String itemsName){
        return goodsService.findAddOutBillByItemsName(itemsName);
    }

    @RequestMapping("/checkOutBill")
    public String checkInBill(){
        return "checkOutBill";
    }

    //获取所有出库表数据
    @RequestMapping("/outBillPresentByOutBillID")
    public String OutBillPresentByOutBillID(int outBillID,Model model){
        List<OutBillPresent> allOutBillPresent = outBillService.findOutBillPresentByOutBillID(outBillID);
        model.addAttribute("outBillPresentList",allOutBillPresent);
        return "checkOutBill";
    }

    @RequestMapping("/makeOutBillCheckStatus")
    @ResponseBody
    public AjaxMesg makeOutBillCheckStatus(int outBillID,String checkMessage,Boolean status,HttpSession session){
        String mesg="";
        User user = (User)session.getAttribute("user");
        if(user == null){
            return new AjaxMesg(false,"请先登录！");
        }

        OutBill outBill = new OutBill();
        outBill.setOutBillID(outBillID);
        outBill.setCheckMessage(checkMessage);
        outBill.setCheckTime(GetTime.getTime());
        outBill.setChecker(user.getUserID());

        if (status){
            outBill.setCheckStatus(1);
            mesg="审批状态：已通过!";
        }
        else{
            outBill.setCheckStatus(2);
            mesg="审批状态：未通过!";
        }
        Integer i = outBillService.updateCheckByOutBillID(outBill);
        if (i>0)
            return new AjaxMesg(true,mesg);
        else
            return new AjaxMesg(false,"审核失败！");
    }

    @RequestMapping("/updateOutBill")
    @ResponseBody
    public AjaxMesg updateOutBill(HttpSession session,String itemsOutJsonListString, String outBillString) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        List<ItemsOut> itemsOutList = mapper.readValue(itemsOutJsonListString,new TypeReference<List<ItemsOut>>() { });

        JSONObject jsonobject = JSONObject.fromObject(outBillString);
        OutBill outBill= (OutBill)JSONObject.toBean(jsonobject,OutBill.class);


        //根据id，更新出库表信息
        Integer update = outBillService.updateOutBill(outBill);
        if (update<1){
            return new AjaxMesg(false,"出库信息更新失败！");
        }

        String month = outBill.getOutTime().substring(0,7);
        for (ItemsOut out : itemsOutList) {

            //插入新增的物品
            if (out.getItemsOutID()==null){
                Integer thisTotal = summaryService.getThisAmount(out.getGoodsID(), month);
                if (thisTotal == 0 || thisTotal ==null){
                    Goods goods = goodsService.findGoodsByGoodsID(out.getGoodsID());
                    return new AjaxMesg(false,"物品"+goods.getItemsName()+"在"+month+"月数量为0，无法添加到该出库单");
                }
                Integer i = itemsOutService.insertItemsOut(out);
                if (i<1){
                    return new AjaxMesg(false,"出库物品插入失败！");
                }
            }else{
                //更新物品
                Integer i = itemsOutService.updateItemsOut(out);
                if (i<1){
                    return new AjaxMesg(false,"出库物品更新失败！");
                }
            }
        }

        return new AjaxMesg(true,"恭喜，修改成功！");
    }
}