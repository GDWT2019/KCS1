package com.kcs.rest.dao;

import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.inBillShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InBillDao {
    List<InBill> findAllInBill();

    int count();

    void insertNewBill(InBill inBill);

    List<inBillShow> inBillShowData();

    List<inBillShow> inBillShowPage(@Param("front")int before,@Param("back") int after);

    int countShow();

    int findMaxInBillID();

    InBill findCheckMessageByID(int inBillID);

    void UpdateInBill(InBill inBill);
}
