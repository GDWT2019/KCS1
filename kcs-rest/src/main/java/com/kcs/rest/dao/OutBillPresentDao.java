package com.kcs.rest.dao;

import com.kcs.rest.pojo.OutBillPresent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OutBillPresentDao {
    //查找所有用于展示的outBill
    List<OutBillPresent> findAllOutBillPresent(@Param("begin")int begin,@Param("end")int end,@Param("time1") String time1,@Param("time2") String time2,@Param("itemName") String itemName);

    //根据outBillID查找,
    List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID);

    //查找总数
    Integer outBillPresentCount(@Param("time1") String time1,@Param("time2") String time2,@Param("itemName") String itemName);
}
