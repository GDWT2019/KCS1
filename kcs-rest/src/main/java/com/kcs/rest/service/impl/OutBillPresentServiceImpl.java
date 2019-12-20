package com.kcs.rest.service.impl;

import com.kcs.rest.dao.OutBillPresentDao;
import com.kcs.rest.dao.UserDao;
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
    private UserDao userDao;

    @Override
    public List<OutBillPresent> findAllOutBillPresent(int begin,int end) {
        List<User> userList = userDao.findAllUser();
        List<OutBillPresent> allOutBillPresent = outBillPresentDao.findAllOutBillPresent(begin, end);
        return updatePerson(userList,allOutBillPresent);
    }

    @Override
    public List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID) {
        List<User> userList = userDao.findAllUser();
        List<OutBillPresent> allOutBillPresent = outBillPresentDao.findOutBillPresentByOutBillID(outBillID);
        return updatePerson(userList,allOutBillPresent);
    }

    public List<OutBillPresent> updatePerson(List<User> userList,List<OutBillPresent> outBillPresentList){
        for (OutBillPresent outBillPresent:
                outBillPresentList) {
            for (User user :
                    userList) {
                if ((outBillPresent.getTaker()!=null)&&(outBillPresent.getTaker() == user.getUserID())) {
                    outBillPresent.setTakerName(user.getUserName());
                }
                if ((outBillPresent.getChecker()!=null)&&(outBillPresent.getChecker() == user.getUserID())) {
                    outBillPresent.setCheckerName(user.getUserName());
                }
                if ((outBillPresent.getStoreManager()!=null)&&(outBillPresent.getStoreManager() == user.getUserID())) {
                    outBillPresent.setStoreManagerName(user.getUserName());
                }
                if ((outBillPresent.getOperator()!=null)&&(outBillPresent.getOperator() == user.getUserID())) {
                    outBillPresent.setOperatorName(user.getUserName());
                }
                if ((outBillPresent.getTableMaker()!=null)&&(outBillPresent.getTableMaker() == user.getUserID())) {
                    outBillPresent.setTableMakerName(user.getUserName());
                }
            }
        }
        return outBillPresentList;
    }
}
