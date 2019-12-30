package com.kcs.rest.service;

import com.kcs.rest.pojo.Log;
import com.kcs.rest.pojo.LogPresent;

import java.util.List;

public interface LogService {
    Integer insertLog (Log log);

    List<LogPresent> findAllLog(int begin, int end);

    Integer getLogCount(String time1, String time2, int userID);

    //根据时间段或用户id查找日志记录
    List<LogPresent> findLogByTimeUserID(int begin, int end, String time1, String time2, int userID);

}
