package com.kcs.rest.dao;

import com.kcs.rest.pojo.Summary;

import java.util.List;

public interface SummaryDao {
    List<Summary> findAllSummary();
}
