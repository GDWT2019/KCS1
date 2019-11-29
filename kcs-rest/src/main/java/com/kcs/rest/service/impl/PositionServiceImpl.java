package com.kcs.rest.service.impl;


import com.kcs.rest.dao.PositionDao;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Position;

import com.kcs.rest.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("positionService")
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;


    @Override
    public KcsResult findAllPosition() {
        List<Position> list=positionDao.findAllPosition();
        if(list != null)
            return KcsResult.ok(list);
        else
            return KcsResult.build(500,"未找到该用户数据");
    }
}
