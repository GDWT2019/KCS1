package com.kcs.rest.service.impl;

import com.kcs.rest.dao.InBillDao;
import com.kcs.rest.pojo.InBill;
import com.kcs.rest.pojo.inBillShow;
import com.kcs.rest.service.InBillService;
import com.kcs.rest.utils.LogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("inBillService")
public class InBillServiceImpl implements InBillService {

    @Autowired
    private InBillDao inBillDao;

    @Override
    public List<InBill> findAllInBill() {
        return inBillDao.findAllInBill();
    }

    @Override
    public int count() {
        return inBillDao.count();
    }

    @Override
    public void insertNewBill(InBill inBill) {

        inBillDao.insertNewBill(inBill);
        Integer inBillID = inBill.getInBillID();
        System.out.println("service   "+inBillID);

    }

    @LogAnno(operateType = "查询入库单")
    @Override
    public List<inBillShow> inBillShowData() {
        return inBillDao.inBillShowData();
    }

    @Override
    public List<inBillShow> inBillShowPage(int before, int after) {
        return inBillDao.inBillShowPage(before,after);
    }

    @Override
    public int countShow() {
        return inBillDao.countShow();
    }

    @Override
    public int findMaxInBillID() {
        return inBillDao.findMaxInBillID();
    }

    @Override
    public InBill findCheckMessageByID(int inBillID) {
        return inBillDao.findCheckMessageByID(inBillID);
    }

    @LogAnno(operateType = "更新入库单")
    @Override
    public void UpdateInBill(InBill inBill) {
        inBillDao.UpdateInBill(inBill);
    }
}
