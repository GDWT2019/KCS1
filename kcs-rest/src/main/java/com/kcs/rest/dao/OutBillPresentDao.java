package com.kcs.rest.dao;

import com.kcs.rest.pojo.OutBillPresent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OutBillPresentDao {
    //查找所有用于展示的outBill
    List<OutBillPresent> findAllOutBillPresent(@Param("begin")int begin,@Param("end")int end);

    //根据outBillID查找,
    List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID);
}
