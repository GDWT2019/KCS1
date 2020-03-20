package com.kcs.portal.controller;


import com.kcs.portal.service.GoodsService;
import com.kcs.portal.service.InBillService;
import com.kcs.portal.service.ItemInService;
import com.kcs.portal.service.SummaryService;
import com.kcs.rest.pojo.*;
import com.kcs.rest.utils.AjaxMesg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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

    @Autowired
    private SummaryService summaryService;

    //跳转物品入库记录
    @RequestMapping("/rItemInRecord")
    public String RItemInRecord(HttpServletRequest request) {

        return "ItemInRecord";
    }


    @RequestMapping("/addInBill")
    @PreAuthorize("hasAnyAuthority('添加入库,入库,ROLE_ADMIN')")
    public String AddInBill(HttpServletRequest request) {

        Date reDate = new Date(System.currentTimeMillis());
        String ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(reDate);
        request.setAttribute("loadtime", ft);

        int maxInBillID = inBillService.findMaxInBillID();
        request.setAttribute("newInBillID", maxInBillID + 1);

        return "addInBill";
    }

    @RequestMapping("/checkInBill")
    @PreAuthorize("hasAnyAuthority('入库审批,入库,ROLE_ADMIN')")
    public String checkInBill(HttpServletRequest request) {

        /*Date reDate = new Date(System.currentTimeMillis());
        String ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(reDate);
        request.setAttribute("loadtime", ft);*/



        return "checkInBill";
    }

    //返回用户数据页面
    @RequestMapping("/rInBill")
    @PreAuthorize("hasAnyAuthority('入库查询全部记录,入库查询个人记录,入库,ROLE_ADMIN')")
    public String RInBill() {
        return "InBill";
    }

    //获取入库单数据数据
    @RequestMapping(value = "InBillPrint", produces = "text/html;charset=utf-8")
    public @ResponseBody
    @PreAuthorize("hasAnyAuthority('入库导出,入库,ROLE_ADMIN')")
    String InBillPrint(HttpServletRequest request) {

        List<inBillShow> list = inBillService.findInBillShow();
        int count = inBillService.countShow();
        request.getSession().setAttribute("count", count);

        JSONArray json = JSONArray.fromObject(list);
        String js = json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + js + "}";
        System.out.println(jso);
        return jso;
    }

    //获取入库单数据数据
    @RequestMapping(value = "inBillShowData", produces = "text/html;charset=utf-8")
    @PreAuthorize("hasAnyAuthority('入库查询全部记录,入库查询个人记录,入库,ROLE_ADMIN')")
    public @ResponseBody
    String InBillData(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Iterator<? extends GrantedAuthority> iterator = authentication.getAuthorities().iterator();
        boolean b =false;
        while (iterator.hasNext()){
            GrantedAuthority next = iterator.next();
            if("入库查询全部记录".equals(next.getAuthority())||"入库".equals(next.getAuthority())||"ROLE_ADMIN".equals(next.getAuthority())){
//            if (iterator.next().toString().equals("入库查询全部记录")||iterator.next().toString().equals("入库")||iterator.next().toString().equals("ROLE_ADMIN")){
                b = true;
            }
        }

        if(b==false){
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            String time1 = request.getParameter("time1");
            String time2 = request.getParameter("time2");
            String itemName = request.getParameter("itemName");
            User user = (User) request.getSession().getAttribute("user");
            String username = user.getUserName();
            String status = request.getParameter("checkStatus");
//        Integer checkStatus = Integer.parseInt(request.getParameter("checkStatus"));
            Integer checkStatus = null;
            if ("".equals(status)) {
                checkStatus = null;
            } else {
                checkStatus = Integer.parseInt(request.getParameter("checkStatus"));
            }

            int before = limit * (page - 1) + 1;
            int after = page * limit;

            List<inBillShow> list = inBillService.PageInBillShow(before, after, time1, time2, itemName, username,checkStatus);
            int count = inBillService.countReload(time1, time2, itemName,username, checkStatus);
            request.getSession().setAttribute("count", count);
            JSONArray json = JSONArray.fromObject(list);
            String js = json.toString();
            String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + js + "}";
            System.out.println(jso);
            return jso;

        }else{

            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            String time1 = request.getParameter("time1");
            String time2 = request.getParameter("time2");
            String itemName = request.getParameter("itemName");
            String username = request.getParameter("username");
            String status = request.getParameter("checkStatus");
//        Integer checkStatus = Integer.parseInt(request.getParameter("checkStatus"));
            Integer checkStatus = null;
            if ("".equals(status)) {
                checkStatus = null;
            } else {
                checkStatus = Integer.parseInt(request.getParameter("checkStatus"));
            }

            int before = limit * (page - 1) + 1;
            int after = page * limit;

            List<inBillShow> list = inBillService.PageInBillShow(before, after, time1, time2, itemName, username,checkStatus);
            int count = inBillService.countReload(time1, time2, itemName,username, checkStatus);
            request.getSession().setAttribute("count", count);
            JSONArray json = JSONArray.fromObject(list);
            String js = json.toString();
            String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + js + "}";
            System.out.println(jso);
            return jso;
        }



    }

    //获取入库记录数据
    @RequestMapping(value = "ItemInRecord", produces = "text/html;charset=utf-8")
    public @ResponseBody
    String ItemInRecord(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        int goodsID = Integer.parseInt(request.getParameter("goodsID"));


        int before = limit * (page - 1) + 1;
        int after = page * limit;

        List<inBillShow> list = inBillService.ItemInRecord(before, after, goodsID);
        int count = inBillService.CountItemInRecord(goodsID);

        JSONArray json = JSONArray.fromObject(list);
        String js = json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":" + js + "}";
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

    @RequestMapping("/getCheckMessageByID")
    @ResponseBody
    public InBill getCheckMessageByID(HttpServletRequest request) {
        String inBillID = request.getParameter("InBillID");

        InBill inBill = inBillService.findCheckMessageByID(inBillID);

        return inBill;
    }

    //插入物品到入库单
    @RequestMapping("/insertBill")
    @ResponseBody
    public void insertBill(HandleAffair list, HttpServletRequest request) {
        String alTotal = request.getParameter("alTotal");
        String inBillTime = request.getParameter("InBillTime");
        String providerID = request.getParameter("providerID");
        String operatorID = request.getParameter("operator");
        String StoreManager = request.getParameter("warehouse");
        String buyer = request.getParameter("buyer");
        String Checker = request.getParameter("Approvaler");
        String TableMaker = request.getParameter("lister");

        InBill inBill = new InBill();
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


        System.out.println(inBillTime + "--" + providerID + "--" + "--" + StoreManager + "--" + buyer + "--" + TableMaker + "--" + operatorID + "--" + alTotal);

        if (inBill.getOperator() != null && inBill.getTimeIn() != null && inBill.getProviderID() != null && inBill.getOperateTime() != null && inBill.getBuyer() != null && inBill.getBuyTime() != null && inBill.getTableMaker() != null && inBill.getStoreManager() != null && inBill.getAllTotal() != null && inBill.getAllTotal() > 0) {
            Integer inBillID = inBillService.insertNewBill(inBill);//插入新单号
            System.out.println(inBillID);

            for (ItemIn itemIn : list.getItemInList()) {
                System.out.println(itemIn);
                itemIn.setInBillID(inBillID);
                if (itemIn.getGoodsID() != null && itemIn.getItemNum() > 0 && itemIn.getItemPrice() > 0) {
                    itemInService.insertNewItem(itemIn); //向新单号插入物品
                }
            }

        }
    }

    //插入物品到入库单
    @RequestMapping("/updateBill")
    @PreAuthorize("hasAnyAuthority('入库修改,入库,ROLE_ADMIN')")
    @ResponseBody
    public void updateBill(HandleAffair list, HttpServletRequest request) {
        String inBillTime = request.getParameter("InBillTime");
        String providerID = request.getParameter("providerID");
        String inBillID = request.getParameter("InBillID");
        String alTotal = request.getParameter("alTotal");
        String operatorID = request.getParameter("operator");

        String StoreManager = request.getParameter("warehouse");
        String buyer = request.getParameter("buyer");
        String Checker = request.getParameter("Approvaler");
        String TableMaker = request.getParameter("lister");

        System.out.println(inBillID + "--" + inBillTime + "--" + providerID + "--" + StoreManager + "--" + buyer + "--" + TableMaker + "--" + operatorID + "--" + alTotal);

        InBill inBill = new InBill();
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
        inBill.setInBillID(Integer.parseInt(inBillID));


        if (inBill.getOperator() != null && inBill.getProviderID() != null && inBill.getOperateTime() != null && inBill.getBuyer() != null && inBill.getBuyTime() != null && inBill.getTableMaker() != null && inBill.getStoreManager() != null && inBill.getAllTotal() != null && inBill.getAllTotal() > 0) {

            inBillService.updateInBillByID(inBill);
            itemInService.delItemByInBillID(inBillID);

            for (ItemIn itemIn : list.getItemInList()) {
                System.out.println(itemIn);
                itemIn.setInBillID(Integer.parseInt(inBillID));
                if (itemIn.getGoodsID() != null && itemIn.getItemNum() > 0 && itemIn.getItemPrice() > 0) {
                    itemInService.insertNewItem(itemIn);
                }
            }

        }

    }

    @RequestMapping("/checkInAmountbiggerOutAmonut")
    @ResponseBody
    public Integer checkInAmountbiggerOutAmonut(HandleAffair list, HttpServletRequest request) {
        String inBillTime = request.getParameter("InBillTime");
        String subTime = inBillTime.substring(0, 7);
        for (ItemIn itemIn : list.getItemInList()) {
            Summary thismonthSummary = summaryService.findThisMonthInAmountByGoodsID(itemIn.getGoodsID(), subTime);
            Integer allInAmout = summaryService.findAllInAmout(itemIn.getGoodsID());
            Integer allOutAmout = summaryService.findAllOutAmout(itemIn.getGoodsID());
            if (thismonthSummary != null) {
                Integer total = allInAmout - thismonthSummary.getInAmount() + itemIn.getItemNum() - allOutAmout;
                if (total < 0) {
                    return -1;
                }
            } else if (thismonthSummary == null) {
                Integer total = allInAmout + itemIn.getItemNum() - allOutAmout;
                if (total < 0) {
                    return -1;
                }
            }
        }
        return 1;
    }

    @RequestMapping("/checkInAmountbiggerOutAmonutForDel")
    @ResponseBody
    public Integer checkInAmountbiggerOutAmonutForDel(HttpServletRequest request) {
        String itemsInID = request.getParameter("ItemsInID");
        ItemIn delItem = itemInService.findItemsInByItemsID(itemsInID);
        Integer allInAmout = summaryService.findAllInAmout(delItem.getGoodsID());
        Integer allOutAmout = summaryService.findAllOutAmout(delItem.getGoodsID());
        Integer total = allInAmout - delItem.getItemNum() - allOutAmout;
        if (total < 0) {
            return -1;
        } else {

            return 1;
        }
    }

    @RequestMapping("/checkInAmountbiggerOutAmonutForRemove")
    @ResponseBody
    public Integer checkInAmountbiggerOutAmonutForRemove(HttpServletRequest request) {
        Integer itemsNum = Integer.parseInt(request.getParameter("itemsNum"));
        String itemsName = request.getParameter("itemsName");
        String itemsType = request.getParameter("itemsType");
        Goods g =new Goods();
        g.setItemsName(itemsName);
        g.setItemsType(itemsType);
        Goods goods = goodsService.findGoodsByItemsNameAndItemsType(g);
        Integer allInAmout = summaryService.findAllInAmout(goods.getGoodsID());
        Integer allOutAmout = summaryService.findAllOutAmout(goods.getGoodsID());
        Integer total = allInAmout - itemsNum - allOutAmout;
        if (total < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
