package com.kcs.rest.service.impl;

import com.kcs.rest.dao.OutBillDao;
import com.kcs.rest.dao.SummaryDao;
import com.kcs.rest.pojo.OutBill;
import com.kcs.rest.pojo.OutBillPresent;
import com.kcs.rest.service.OutBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("outBillService")
public class OutBillServiceImpl implements OutBillService {

    @Autowired
    private OutBillDao outBillDao;
    @Autowired
    private SummaryDao summaryDao;

    @Override
    public Integer insertOutBill(OutBill outBill) {
        return outBillDao.insertOutBill(outBill);
    }

    @Override
    public Integer findTheMaxOutBillID() {
        return outBillDao.findTheMaxOutBillID();
    }

    @Override
    public OutBill findOutBillByID(int outBillID) {
        return outBillDao.findOutBillByID(outBillID);
    }

    @Override
    public Integer updateCheckByOutBillID(OutBill outBill) {
        return outBillDao.updateCheckByOutBillID(outBill);
    }

    @Override
    public Integer updateOutBill(OutBill outBill) {
        return outBillDao.updateOutBill(outBill);
    }

    @Override
    public List<OutBillPresent> ItemOutRecord(int front, int back, int id) {
        return outBillDao.ItemOutRecord(front,back,id);
    }

    @Override
    public int CountItemOutRecord(int goodsid) {
        return outBillDao.CountItemOutRecord(goodsid);
    }
}
