package com.kcs.portal.service.impl;

import com.kcs.portal.service.SummaryService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.SummartAndGoodsAndCategory;
import com.kcs.rest.pojo.Summary;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import com.kcs.rest.utils.Rest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("summaryService")
public class SummaryServiceImpl implements SummaryService {
    @Override
    public Summary findSummaryByGoodsIDAndDate(Summary s) {
        try {
            String json = HttpClientUtil.doPostJson(Rest.rest+"summary/getSummaryByGoodsIDAndTime", JsonUtils.objectToJson(s));
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
            String json = HttpClientUtil.doPost(Rest.rest+"summary/getGoodsIDByTime" + Time);
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
            String json = HttpClientUtil.doPost(Rest.rest+"summary/getSummaryInTheLastGoodsDataByGoodsID" + GoodsID);
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
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findAllTime");
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
            String s = HttpClientUtil.doGet(Rest.rest+"summary/summartyBillData",param);
            KcsResult result = KcsResult.formatToList(s,SummartAndGoodsAndCategory.class);
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
            String s = HttpClientUtil.doGet(Rest.rest+"summary/summaryTotal"+time);
            return Integer.parseInt(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<SummartAndGoodsAndCategory> summartyAllData() {

        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/summartyAllData");
            KcsResult result = KcsResult.formatToList(s,SummartAndGoodsAndCategory.class);
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
            String s = HttpClientUtil.doGet(Rest.rest+"summary/summaryAllTotal");
            return Integer.parseInt(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<SummartAndGoodsAndCategory> summaryAllCurrentdata(int before, int after,String time1, String time2,String itemName) {
        HashMap<String, String> param = new HashMap<>();
        param.put("before",before+ "");
        param.put("after",after+ "");
        param.put("time1",time1);
        param.put("time2",time2);
        param.put("itemName",itemName);

        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/summaryAllCurrentdata",param);
            KcsResult result = KcsResult.formatToList(s,SummartAndGoodsAndCategory.class);
            if (result.getStatus() == 200) {
                return (List<SummartAndGoodsAndCategory>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countReload(String time1, String time2,String itemName) {
        HashMap<String, String> param = new HashMap<>();
        param.put("time1",time1);
        param.put("time2",time2);
        param.put("itemName",itemName);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/countReload",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (int) result.getData();
            }
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
            String s = HttpClientUtil.doGet(Rest.rest+"summary/getThisTotal",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Summary findThisMonthInAmountByGoodsID(Integer goodsID, String subTime) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("subTime",subTime);

        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findThisMonthInAmountByGoodsID",param);
            KcsResult result = KcsResult.formatToPojo(s, Summary.class);
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
    public Integer findAllInAmout(Integer goodsID) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findAllInAmout"+goodsID);
            return Integer.parseInt(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer findAllOutAmout(Integer goodsID) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findAllOutAmout"+goodsID);
            return Integer.parseInt(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void export() {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/poiSummary");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer findAllBeforeInAmout(Integer goodsID, String subTime) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("subTime",subTime);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findAllBeforeInAmout",param);
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer findAllBeforeOutAmout(Integer goodsID, String subTime) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("subTime",subTime);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findAllBeforeOutAmout",param);
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer findAllafterInAmout(Integer goodsID, String subTime) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("subTime",subTime);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findAllafterInAmout",param);
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer findAllafterOutAmout(Integer goodsID, String subTime) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("subTime",subTime);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findAllafterOutAmout",param);
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Summary findlatestAfterSummary(Integer goodsID, String subTime) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("subTime",subTime);

        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findlatestAfterSummary",param);
            KcsResult result = KcsResult.formatToPojo(s, Summary.class);
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
    public Summary findLongestAfterSummary(Integer goodsID, String subTime) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("subTime",subTime);

        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findLongestAfterSummary",param);
            KcsResult result = KcsResult.formatToPojo(s, Summary.class);
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
    public Integer findBetweenBeforeAndAffterInAmout(Integer goodsID, String subTime, String subTime1) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("subTime",subTime);
        param.put("subTime1",subTime1);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findBetweenBeforeAndAffterInAmout",param);
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer findBetweenBeforeAndAffterOutAmout(Integer goodsID, String subTime, String subTime1) {
        HashMap<String, String> param = new HashMap<>();
        param.put("goodsID",goodsID+ "");
        param.put("subTime",subTime);
        param.put("subTime1",subTime1);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"summary/findBetweenBeforeAndAffterOutAmout",param);
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

