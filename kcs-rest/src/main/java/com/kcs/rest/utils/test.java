package com.kcs.rest.utils;

import com.kcs.rest.dao.GoodsDao;
import com.kcs.rest.dao.InBillDao;
import com.kcs.rest.dao.ItemInDao;
import com.kcs.rest.pojo.*;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext-dao.xml", "classpath:spring/applicationContext-service.xml" })
public class test {
    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private InBillDao inBillDao;

    @Autowired
    private ItemInDao itemInDao;


    @Test
    public void test5(){
        ItemIn itemIn=new ItemIn();
//       itemInDao.insertNewBill();

    }@Test
    public void test4(){
       itemInDao.delItem(113);

    }

    @Test
    public void test3(){
        List<inBillShow> inBillShows = inBillDao.inBillShowData();
        for (inBillShow inBillShow : inBillShows) {
            System.out.println(inBillShow);
        }
    }



    @Test
    public void test2(){
        List<ItemsShow> itemsInData = itemInDao.findItemsInData(149);
        for (ItemsShow itemsInDatum : itemsInData) {
            System.out.println(itemsInDatum);
        }
    }

    @Test
    public void test(){
        List<Goods> goodsInSummaryGoodsID = goodsDao.findGoodsBygoodsId(7);
        for (Goods goods : goodsInSummaryGoodsID) {
            System.out.println(goods);
        }
    }
    @Test
    public void test1(){
        InBill inBill=new InBill();
        inBill.setTimeIn("2019-12-06 14:36:03");
        inBill.setProviderID(1);
        inBill.setOperator(1);
        inBill.setOperateTime("2019-12-06 14:36:03");
        inBill.setBuyer(1);
        inBill.setBuyTime("2019-12-06 14:36:03");
        inBill.setChecker(1);
        inBill.setTableMaker(1);
        inBill.setStoreManager(3);
        inBill.setCheckStatus(0);
        inBill.setAllTotal(77.0);
        inBillDao.insertNewBill(inBill);
        Integer inBillID = inBill.getInBillID();
        System.out.println(inBillID);
    }
}
