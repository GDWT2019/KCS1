package com.kcs.rest.service.impl;

import com.kcs.rest.dao.OutBillPresentDao;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.OutBillPresent;
import com.kcs.rest.pojo.User;
import com.kcs.rest.service.OutBillPresentService;
import com.kcs.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("outBillPresentService")
public class OutBillPresentServiceImpl implements OutBillPresentService {

    @Autowired
    private OutBillPresentDao outBillPresentDao;
    @Autowired
    private UserService userService;

    @Override
    public List<OutBillPresent> findAllOutBillPresent() {
        List<User> userList = userService.AllUser();

        List<OutBillPresent> allOutBillPresent = outBillPresentDao.findAllOutBillPresent();
        for (OutBillPresent outBillPresent:
             allOutBillPresent) {
            for (User user :
                    userList) {
                if (outBillPresent.getTaker() == user.getUserID()) {
                    outBillPresent.setTakerName(user.getUserName());
                }
                if (outBillPresent.getChecker() == user.getUserID()) {
                    outBillPresent.setCheckerName(user.getUserName());
                }
                if (outBillPresent.getStoreManager() == user.getUserID()) {
                    outBillPresent.setStoreManagerName(user.getUserName());
                }
                if (outBillPresent.getOperator() == user.getUserID()) {
                    outBillPresent.setOperatorName(user.getUserName());
                }
                if (outBillPresent.getTableMaker() == user.getUserID()) {
                    outBillPresent.setTableMakerName(user.getUserName());
                }
                }
        }
        return allOutBillPresent;
    }

    @Override
    public List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID) {
        List<User> userList = userService.AllUser();

        List<OutBillPresent> allOutBillPresent = outBillPresentDao.findOutBillPresentByOutBillID(outBillID);
        for (OutBillPresent outBillPresent:
                allOutBillPresent) {
            for (User user :
                    userList) {
                if (outBillPresent.getTaker() == user.getUserID()) {
                    outBillPresent.setTakerName(user.getUserName());
                }
                if (outBillPresent.getChecker() == user.getUserID()) {
                    outBillPresent.setCheckerName(user.getUserName());
                }
                if (outBillPresent.getStoreManager() == user.getUserID()) {
                    outBillPresent.setStoreManagerName(user.getUserName());
                }
                if (outBillPresent.getOperator() == user.getUserID()) {
                    outBillPresent.setOperatorName(user.getUserName());
                }
                if (outBillPresent.getTableMaker() == user.getUserID()) {
                    outBillPresent.setTableMakerName(user.getUserName());
                }
            }
        }
        return allOutBillPresent;
    }

}
