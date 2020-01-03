package com.kcs.rest.service.impl;

import com.kcs.rest.dao.OutBillPresentDao;
import com.kcs.rest.dao.UserDao;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.OutBillPresent;
import com.kcs.rest.pojo.User;
import com.kcs.rest.service.OutBillPresentService;
import com.kcs.rest.service.UserService;
import com.kcs.rest.utils.LogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("outBillPresentService")
public class OutBillPresentServiceImpl implements OutBillPresentService {

    @Autowired
    private OutBillPresentDao outBillPresentDao;
    @Autowired
    private UserDao userDao;

    @LogAnno(operateType = "查看出库明细")
    @Override
    public List<OutBillPresent> findAllOutBillPresent(int begin,int end,String time1,String time2,String itemName) {
        List<User> userList = userDao.findAllUser();
        List<OutBillPresent> allOutBillPresent = outBillPresentDao.findAllOutBillPresent(begin, end,time1,time2,itemName);
        return updatePerson(userList,allOutBillPresent);
    }

    @Override
    public List<OutBillPresent> findOutBillPresentByOutBillID(int outBillID) {
        List<User> userList = userDao.findAllUser();
        List<OutBillPresent> allOutBillPresent = outBillPresentDao.findOutBillPresentByOutBillID(outBillID);
        return updatePerson(userList,allOutBillPresent);
    }

    @Override
    public Integer outBillPresentCount(String time1, String time2, String itemName) {
        return outBillPresentDao.outBillPresentCount(time1, time2, itemName);
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
