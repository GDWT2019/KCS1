package com.kcs.portal.service.impl;

import com.kcs.portal.service.SummaryService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Summary;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import org.springframework.stereotype.Service;

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
}

