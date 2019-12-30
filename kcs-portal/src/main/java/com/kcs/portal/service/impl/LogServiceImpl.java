package com.kcs.portal.service.impl;

import com.kcs.portal.service.LogService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Log;
import com.kcs.rest.pojo.LogPresent;
import com.kcs.rest.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("logService")
public class LogServiceImpl implements LogService {
    @Override
    public Integer insertLog(Log log) {
        return null;
    }

    @Override
    public List<LogPresent> findAllLog(int begin, int end) {
        HashMap<String, String> param = new HashMap<>();
        param.put("begin",begin+ "");
        param.put("end",end+ "");
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/log/findAllLog",param);
            KcsResult result = KcsResult.formatToList(s,LogPresent.class);
            if (result.getStatus() == 200) {
                List<LogPresent> LogPresentList = (List<LogPresent>) result.getData();
                return LogPresentList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getLogCount(String time1, String time2, int userID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("time1",time1);
        param.put("time2",time2);
        param.put("userID",userID+ "");
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/log/getLogCount",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                Integer count = (Integer) result.getData();
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LogPresent> findLogByTimeUserID(int begin, int end, String time1, String time2, int userID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("begin",begin+ "");
        param.put("end",end+ "");
        param.put("time1",time1);
        param.put("time2",time2);
        param.put("userID",userID+ "");
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/log/findLogByTimeUserID",param);
            KcsResult result = KcsResult.formatToList(s,LogPresent.class);
            if (result.getStatus() == 200) {
                List<LogPresent> LogPresentList = (List<LogPresent>) result.getData();
                return LogPresentList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
