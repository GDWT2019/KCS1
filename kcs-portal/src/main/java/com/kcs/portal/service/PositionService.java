package com.kcs.portal.service;



import com.kcs.rest.pojo.Position;

import java.util.List;

public interface PositionService {

    List<Position> findAllPosition();

    //新增职位
    Integer addPosition(String positionName);
}
