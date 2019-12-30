package com.kcs.rest.service.impl;


import com.kcs.rest.dao.PositionDao;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Position;

import com.kcs.rest.service.PositionService;
import com.kcs.rest.utils.LogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("positionService")
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;


    @Override
    public List<Position> findAllPosition() {
        return positionDao.findAllPosition();


    }

    @LogAnno(operateType = "新增职位")
    @Override
    public Integer addPosition(String positionName) {
        return positionDao.addPosition(positionName);
    }
}
