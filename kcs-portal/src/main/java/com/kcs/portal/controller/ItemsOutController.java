package com.kcs.portal.controller;

import com.kcs.portal.service.ItemsOutService;
import com.kcs.rest.utils.AjaxMesg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("itemsOutController")
@RequestMapping("/itemsOut")
public class ItemsOutController {

    @Autowired
    private ItemsOutService itemsOutService;

    @RequestMapping("/delByItemsOutID")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('出库删除,出库,ROLE_ADMIN')")
    public AjaxMesg delByItemsOutID(int itemsOutID){
        int i = itemsOutService.delItemsOutByID(itemsOutID);

        if (i>0)
            return new AjaxMesg(true,"删除成功！");
        else
            return new AjaxMesg(false,"删除失败！");
    }

//    @RequestMapping(value = "/getItemsOut")
//    public String itemsOutDataByOutBillID(int outBillID){
//
//    }
}
