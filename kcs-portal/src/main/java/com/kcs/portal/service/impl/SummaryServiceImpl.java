package com.kcs.portal.service.impl;

import com.kcs.portal.service.SummaryService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.SummartAndGoodsAndCategory;
import com.kcs.rest.pojo.Summary;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("summaryService")
public class SummaryServiceImpl implements SummaryService {
    @Override
    public Summary findSummaryByGoodsIDAndDate(Summary s) {
        try {
            String json = HttpClientUtil.doPostJson("http://localhost:8081/kcs_rest_war/summary/getSummaryByGoodsIDAndTime", JsonUtils.objectToJson(s));
            KcsResult result = KcsResult.formatToPojo(json, Summary.class);
            if (result.getStatus() == 200) {
                Summary summary = (Summary) result.getData();
                return summary;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Integer> findGoodsIDByTime(String Time) {
        try {
            String json = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/summary/getGoodsIDByTime" + Time);
            KcsResult result = KcsResult.formatToPojo(json, Integer.class);
            if (result.getStatus() == 200) {
                List<Integer> GoodsIDList = (List<Integer>) result.getData();
                return GoodsIDList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Summary findSummaryInTheLastGoodsDataByGoodsID(int GoodsID) {
        try {
            String json = HttpClientUtil.doPost("http://localhost:8081/kcs_rest_war/summary/getSummaryInTheLastGoodsDataByGoodsID" + GoodsID);
            KcsResult result = KcsResult.formatToPojo(json, Summary.class);
            if (result.getStatus() == 200) {
                Summary summary = (Summary) result.getData();
                return summary;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SummartAndGoodsAndCategory> findAllTime() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/summary/findAllTime");
            KcsResult result = KcsResult.formatToList(s, SummartAndGoodsAndCategory.class);
            if (result.getStatus() == 200) {
                List<SummartAndGoodsAndCategory> sgcList = (List<SummartAndGoodsAndCategory>) result.getData();
                return sgcList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SummartAndGoodsAndCategory> summartyBillData(int before, int after, String time) {
        HashMap<String, String> param = new HashMap<>();
        param.put("before",before+ "");
        param.put("after",after+ "");
        param.put("time",time);
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/summary/summartyBillData",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (List<SummartAndGoodsAndCategory>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countSummary(String time) {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/summary/summaryTotal"+time);
            return Integer.parseInt(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<SummartAndGoodsAndCategory> summartyAllData() {

        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/summary/summartyAllData");
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (List<SummartAndGoodsAndCategory>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countAll() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/summary/summaryAllTotal");
            return Integer.parseInt(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<SummartAndGoodsAndCategory> summaryAllCurrentdata(int before, int after,String itemName) {
        HashMap<String, String> param = new HashMap<>();
        param.put("before",before+ "");
        param.put("after",after+ "");
        param.put("itemName",itemName);

        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/summary/summaryAllCurrentdata",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (List<SummartAndGoodsAndCategory>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countReload(String itemName) {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/summary/countReload"+itemName);
            return Integer.parseInt(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getThisAmount(int goodsID, String time) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("time",time);

        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/summary/getThisTotal",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}

