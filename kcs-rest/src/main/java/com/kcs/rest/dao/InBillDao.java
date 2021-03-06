package com.kcs.rest.dao;

import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.inBillShow;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface InBillDao {
    List<InBill> findAllInBill();

    int count();

    void insertNewBill(InBill inBill);

    List<inBillShow> inBillShowData();

    List<inBillShow> inBillShowPage(@Param("front")int before,@Param("back") int after,@Param("time1") String time1,@Param("time2") String time2,@Param("itemName") String itemName,@Param("Invoice") String Invoice,@Param("username") String username,@Param("checkStatus") Integer checkStatus);

    int countShow();

    int findMaxInBillID();

    InBill findCheckMessageByID(int inBillID);

    void UpdateInBill(InBill inBill);

    String findTimeByID(Integer inBillID);

    int countReload(@Param("time1") String time1,@Param("time2") String time2,@Param("itemName") String itemName,@Param("Invoice") String Invoice,@Param("username") String username,@Param("checkStatus") Integer checkStatus);

    List<inBillShow> ItemInRecord(@Param("front")int front, @Param("back")int back, @Param("goodsId")int goodsId);

    int CountItemInRecord(int goodsid);

    List<inBillShow> findPresentItemIn(@Param("subTime")String subTime, @Param("goodsID") Integer goodsID);

    void updateInBillAlltotalByID(@Param("nowAllTotal") Float nowAllTotal, @Param("inBillID") Integer inBillID);
}
