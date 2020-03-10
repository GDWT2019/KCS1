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
    public Integer getLogCount(String time1, String time2, String name) {
        return logDao.getLogCount(time1, time2, name);
    }

    @LogAnno(operateType = "搜索用户日志")
    @Override
    public List<LogPresent> findLogByTimeName(int begin, int end, String time1, String time2, String name) {
        return logDao.findLogByTimeName(begin, end, time1, time2, name);
    }
}
