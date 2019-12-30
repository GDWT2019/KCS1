package com.kcs.rest.service;



import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Position;

import java.util.List;

public interface PositionService {

    List<Position> findAllPosition();

    Integer addPosition(String positionName);
}
