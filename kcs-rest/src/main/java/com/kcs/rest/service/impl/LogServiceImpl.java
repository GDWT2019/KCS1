package com.kcs.rest.service.impl;

import com.kcs.rest.dao.LogDao;
import com.kcs.rest.pojo.Log;
import com.kcs.rest.pojo.LogPresent;
import com.kcs.rest.service.LogService;
import com.kcs.rest.utils.LogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;
    @Override
    public Integer insertLog(Log log) {
        return logDao.insertLog(log);
    }

    @LogAnno(operateType = "查看用户日志")
    @Override
    public List<LogPresent> findAllLog(int begin, int end) {
        return logDao.findAllLog(begin, end);
    }

    @Override
    public Integer getLogCount(String time1, String time2, int userID) {
        return logDao.getLogCount(time1, time2, userID);
    }

    @LogAnno(operateType = "搜索用户日志")
    @Override
    public List<LogPresent> findLogByTimeUserID(int begin, int end, String time1, String time2, int userID) {
        return logDao.findLogByTimeUserID(begin, end, time1, time2, userID);
    }
}
