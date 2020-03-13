package com.kcs.portal.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.kcs.portal.service.SummaryService;
import com.kcs.rest.pojo.SummartAndGoodsAndCategory;
import com.kcs.rest.utils.FileUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONArray;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Controller("summaryController")
@RequestMapping("/summary")
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    //返回汇总数据页面
    @RequestMapping("/rCurrentBill")
    public String RCurrentBill(){
        return "currentBill";
    }

    //返回汇总数据页面
    @RequestMapping("/rSummary")
    public String RInBill(){
        return "summaryBill";
    }

    //获取时间
    @RequestMapping(value="findAllTime",produces="text/html;charset=utf-8")
    public @ResponseBody String findAllTime(){
        List<SummartAndGoodsAndCategory> list=summaryService.findAllTime();
        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        System.out.println(js);
        return js;
    }

    //获取汇总单数据数据
    @RequestMapping(value="summartyBillData",produces="text/html;charset=utf-8")
    public @ResponseBody
    String summartyBillData(HttpServletRequest request){
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        String time = request.getParameter("time");
        System.out.println("time"+time);

        int before=limit*(page-1)+1;
        int after = page * limit;

        List<SummartAndGoodsAndCategory> list=summaryService.summartyBillData(before,after,time);
        int count =summaryService.countSummary(time);
        request.getSession().setAttribute("count",count);

        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;
    }


    //获取汇总单数据数据
    @RequestMapping(value="summaryAllData",produces="text/html;charset=utf-8")
    public @ResponseBody
    String summaryAllData(HttpServletRequest request){

        List<SummartAndGoodsAndCategory> list=summaryService.summartyAllData();
        int count =summaryService.countAll();

        request.setAttribute("length",list.size());
        System.out.println("list.size()"+list.size());

        JSONArray json = JSONArray.fromObject(list);
        String js=json.toString();
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;
    }

      //获取流水单数据数据
        @RequestMapping(value="summaryAllCurrentdata",produces="text/html;charset=utf-8")
        public @ResponseBody
        String summaryAllCurrentdata(HttpServletRequest request){
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            String itemName = request.getParameter("itemName");


            int before=limit*(page-1)+1;
            int after = page * limit;

            List<SummartAndGoodsAndCategory> list=summaryService.summaryAllCurrentdata(before,after,itemName);
            int count =summaryService.countReload(itemName);

            JSONArray json = JSONArray.fromObject(list);
            String js=json.toString();
            String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
            System.out.println(jso);
            return jso;
        }


        /*@RequestMapping("/poiSummary")
        public void  poiSummary(HttpServletResponse response){
            summaryService.export();
        }*/

    @RequestMapping("/poiSummary")
    public void  poiSummary1(HttpServletResponse response){
        List<SummartAndGoodsAndCategory> list=summaryService.summartyAllData();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("汇总表","汇总"),SummartAndGoodsAndCategory .class, list);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("汇总.xls", "UTF-8"));
            response.setHeader("Pragma", "No-cache");//设置不要缓存
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

