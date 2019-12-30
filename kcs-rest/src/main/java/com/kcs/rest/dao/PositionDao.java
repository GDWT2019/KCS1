package com.kcs.rest.dao;

import com.kcs.rest.pojo.Position;

import java.util.List;

public interface PositionDao {
    List<Position> findAllPosition();

    Integer addPosition(String positionName);
}
