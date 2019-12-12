package com.kcs.portal.controller;


import com.kcs.portal.service.GoodsService;
import com.kcs.portal.service.InBillService;
import com.kcs.portal.service.ItemInService;
import com.kcs.rest.pojo.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller("inBillController")
@RequestMapping("/inBill")
public class InBillController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ItemInService itemInService;

    @Autowired
    private InBillService inBillService;

    @RequestMapping("/addInBill")
    public String AddInBill(HttpServletRequest request){

        Date reDate = new Date(System.currentTimeMillis());
        String ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(reDate);
        request.setAttribute("loadtime", ft);

        int maxInBillID=inBillService.findMaxInBillID();
        request.setAttribute("newInBillID",maxInBillID+1);

        return "addInBill";
    }
    @RequestMapping("/checkInBill")
    public String checkInBill(HttpServletRequest request){

        Date reDate = new Date(System.currentTimeMillis());
        String ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(reDate);
        request.setAttribute("loadtime", ft);

        return "checkInBill";
    }

    //返回用户数据页面
    @RequestMapping("/rInBill")
    public String RInBill(){
        return "InBill";
    }

    //获取入库单数据数据
    @RequestMapping(value="InBillPrint",produces="text/html;charset=utf-8")
    public @ResponseBody String InBillPrint(HttpServletRequest request){

        List<inBillShow> list=inBillService.findInBillShow();
        int count =inBillService.countShow();
        request.getSession().setAttribute("count",count);

        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;
    }

    //获取入库单数据数据
    @RequestMapping(value="inBillShowData",produces="text/html;charset=utf-8")
    public @ResponseBody
    String InBillData(HttpServletRequest request){
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        int before=limit*(page-1)+1;
        int after = page * limit;

        List<inBillShow> list=inBillService.PageInBillShow(before,after);
        int count =inBillService.countShow();
        request.getSession().setAttribute("count",count);

        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;
    }

    @RequestMapping("/findGoodsByItemsName")
    @ResponseBody
    public List<Goods> findGoodsByItemName(String itemsName) {
        List<Goods> goodsList = goodsService.findGoodsByItemsName(itemsName);
        return goodsList;
    }

    @RequestMapping("/findGoodsByGoodsID")
    @ResponseBody
    public List<Goods> findGoodsByGoodsID(HttpServletRequest request) {
        String goodsID = request.getParameter("goodsID");

        List<Goods> goodsList = goodsService.findGoodsByGoodsID(goodsID);
        return goodsList;
    }

    //插入物品到入库单
    @RequestMapping("/insertBill")
    @ResponseBody
    public void insertBill(HandleAffair list,HttpServletRequest request){
        String alTotal = request.getParameter("alTotal");
        String inBillTime = request.getParameter("InBillTime");
        String providerID = request.getParameter("providerID");
        String operatorID = request.getParameter("operator");
        String StoreManager = request.getParameter("warehouse");
        String buyer = request.getParameter("buyer");
        String Checker = request.getParameter("Approvaler");
        String TableMaker = request.getParameter("lister");

        InBill inBill=new InBill();
        inBill.setTimeIn(inBillTime);
        inBill.setProviderID(Integer.parseInt(providerID));
        inBill.setOperator(Integer.parseInt(operatorID));
        inBill.setOperateTime(inBillTime);
        inBill.setBuyer(Integer.parseInt(buyer));
        inBill.setBuyTime(inBillTime);
        inBill.setChecker(Integer.parseInt(Checker));
        inBill.setTableMaker(Integer.parseInt(TableMaker));
        inBill.setStoreManager(Integer.parseInt(StoreManager));
        inBill.setCheckStatus(0);
        inBill.setAllTotal(Double.parseDouble(alTotal));
        System.out.println(inBill);


        System.out.println(inBillTime+"--"+providerID+"--"+"--"+StoreManager+"--"+buyer+"--"+TableMaker+"--"+operatorID+"--"+alTotal);

        if(inBill.getOperator()!=null&&inBill.getTimeIn()!=null&&inBill.getProviderID()!=null&&inBill.getOperateTime()!=null&&inBill.getBuyer()!=null&&inBill.getBuyTime()!=null&&inBill.getTableMaker()!=null&&inBill.getStoreManager()!=null&&inBill.getAllTotal()!=null)
        {
            Integer inBillID= inBillService.insertNewBill(inBill);
            System.out.println(inBillID);

          for (ItemIn itemIn : list.getItemInList()) {
            System.out.println(itemIn);
            itemIn.setInBillID(inBillID);
            if(itemIn.getGoodsID()!=null){
           itemInService.insertNewItem(itemIn);
            }
         }
        }



    }
}
