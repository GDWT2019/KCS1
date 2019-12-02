package com.kcs.rest.service.impl;

import com.kcs.rest.dao.OutBillPresentDao;
import com.kcs.rest.pojo.OutBillPresent;
import com.kcs.rest.service.OutBillPresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("outBillPresentService")
public class OutBillPresentServiceImpl implements OutBillPresentService {

    @Autowired
    private OutBillPresentDao outBillPresentDao;

    @Override
    public List<OutBillPresent> findAllOutBillPresent() {
        return outBillPresentDao.findAllOutBillPresent();
    }

}
