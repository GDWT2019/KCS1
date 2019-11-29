package com.kcs.rest.dao;

import com.kcs.rest.pojo.Summary;

import java.util.List;

public interface SummaryDao {

    //查找汇总表所有记录
    List<Summary> findAllSummary();
}
