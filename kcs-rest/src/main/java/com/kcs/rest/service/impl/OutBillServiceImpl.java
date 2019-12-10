package com.kcs.rest.service.impl;

import com.kcs.rest.dao.OutBillDao;
import com.kcs.rest.pojo.OutBill;
import com.kcs.rest.service.OutBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("outBillService")
public class OutBillServiceImpl implements OutBillService {

    @Autowired
    private OutBillDao outBillDao;

    @Override
    public Integer insertOutBill(OutBill outBill) {
        return outBillDao.insertOutBill(outBill);
    }

    @Override
    public Integer findTheMaxOutBillID() {
        return outBillDao.findTheMaxOutBillID();
    }
}
