package com.kcs.rest.service;

import com.kcs.rest.pojo.Summary;

import java.util.List;

public interface SummaryService {

    //查找汇总表所有记录
    List<Summary> findAllSummary();
}

