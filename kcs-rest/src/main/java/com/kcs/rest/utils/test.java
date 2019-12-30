package com.kcs.rest.utils;

import com.kcs.rest.dao.GoodsDao;
import com.kcs.rest.dao.InBillDao;
import com.kcs.rest.dao.ItemInDao;
import com.kcs.rest.dao.SummaryDao;
import com.kcs.rest.pojo.*;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private SummaryDao summaryDao;

    @Test
    public void test14(){
        List<SummartAndGoodsAndCategory> summartAndGoodsAndCategories = summaryDao.summartyAllData();
        System.out.println(summartAndGoodsAndCategories);
    }


    @Test
    public void test13(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String date="2019-12"+"-1";
            Date parse = simpleDateFormat.parse(date);
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String date="2019-12"+"-1";
        Summary beforeMonth = summaryDao.findBeforeMonth(17, date);
        System.out.println(beforeMonth);
    }


    @Test
    public void test12(){

        int inBillByItemsID = itemInDao.findInBillByItemsID(343);
        System.out.println(inBillByItemsID);
    }

    @Test
    public void test11(){
        int summartAndGoodsAndCategories = summaryDao.summaryTotal("2019-11");
        System.out.println(summartAndGoodsAndCategories);
    }

    @Test
    public void test10(){
        List<GoodsAndCategoryAndItemsIn> itemsInList = itemInDao.getItemsInList(157);
        for (GoodsAndCategoryAndItemsIn itemIn : itemsInList) {
            System.out.println("itemIn"+itemIn);
        }
        System.out.println("itemsInList"+itemsInList);

        List<Goods> goodsBygoodsId = goodsDao.findGoodsBygoodsId(5);
        System.out.println("goodsBygoodsId"+goodsBygoodsId);
    }

    @Test
    public void test9(){
        List<GoodsAndCategoryAndItemsIn> itemsInList = itemInDao.getItemsInList(150);
        for (GoodsAndCategoryAndItemsIn itemIn : itemsInList) {
            System.out.println(itemIn);
        }
    }

    @Test
    public void test8(){

        List<Goods> goodsList = goodsDao.findGoodsByItemName("黑色签字笔");
        List<Category> categories = new ArrayList<>();
        for (Goods goods : goodsList) {
            System.out.println(goods);
            Integer categoryID = goods.getCategoryID();
            Category category=goodsDao.findCategoryNameByID(categoryID);
            categories.add(category);
            goods.setCategories(categories);
            System.out.println(goods.getCategories().get(0).getCategoryName());
        }

        System.out.println("goodslist:"+goodsList.get(0).getCategories().get(0).getCategoryName());
    }

    @Test
    public void test7(){
        List<Goods> allGoodsName = goodsDao.findAllGoodsName();
        for (Goods goods : allGoodsName) {
            System.out.println(goods);
        }
    }
    @Test
    public void test6(){
        int maxInBillID = inBillDao.findMaxInBillID();
        System.out.println(maxInBillID);
    }
    @Test
    public void test5(){
        InBill inBill =new InBill();
        inBill.setCheckStatus(2);
        inBill.setInBillID(150);
        itemInDao.UpdateCheckStatus(inBill);
    }
    @Test
    public void test4(){
        System.out.println(GetTime.getTime());
        System.out.println(GetTime.getTime());
        System.out.println(GetTime.getTime());
        System.out.println(GetTime.getTime());
        System.out.println(Integer.parseInt(null));
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
