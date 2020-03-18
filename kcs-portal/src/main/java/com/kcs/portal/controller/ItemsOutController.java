package com.kcs.portal.controller;

import com.kcs.portal.service.ItemsOutService;
import com.kcs.rest.utils.AjaxMesg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

@Controller("itemsOutController")
@RequestMapping("/itemsOut")
public class ItemsOutController {

    @Autowired
    private ItemsOutService itemsOutService;

    @RequestMapping("/delByItemsOutID")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('出库查询全部记录,出库查询本人记录,出库删除,出库,ROLE_ADMIN')")
    public AjaxMesg delByItemsOutID(int itemsOutID){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Iterator<? extends GrantedAuthority> iterator = authentication.getAuthorities().iterator();
        boolean b =false;
        while (iterator.hasNext()){
            if (iterator.next().toString().equals("出库删除")){
                b = true;
            }
        }
        if(!b){
            return new AjaxMesg(false,"无删除权限,无法删除!");
        }
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
