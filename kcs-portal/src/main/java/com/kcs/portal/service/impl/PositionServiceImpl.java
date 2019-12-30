package com.kcs.portal.service.impl;



import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Position;
import com.kcs.rest.pojo.User;
import com.kcs.portal.service.PositionService;

import com.kcs.rest.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("positionService")
public class PositionServiceImpl implements PositionService {


    @Override
    public List<Position> findAllPosition() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/position/positionData");
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {

                return (List<Position>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer addPosition(String positionName) {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/position/addPosition"+positionName);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {

                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
