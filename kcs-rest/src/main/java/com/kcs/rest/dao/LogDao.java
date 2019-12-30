package com.kcs.rest.dao;

import com.kcs.rest.pojo.Log;
import com.kcs.rest.pojo.LogPresent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDao {
    Integer insertLog (Log log);

    List<LogPresent> findAllLog(@Param("begin")int begin, @Param("end")int end);

    Integer getLogCount(@Param("time1") String time1,@Param("time2") String time2,@Param("userID")int userID);

    //时间或用户id皆可为空
    List<LogPresent> findLogByTimeUserID(@Param("begin")int begin, @Param("end")int end,@Param("time1") String time1,@Param("time2") String time2,@Param("userID")int userID);
}
