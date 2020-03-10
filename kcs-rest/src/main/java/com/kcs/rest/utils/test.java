package com.kcs.rest.utils;

import com.kcs.rest.dao.*;
import com.kcs.rest.pojo.*;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml", "classpath:spring/applicationContext-service.xml"})
public class test {
    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private InBillDao inBillDao;

    @Autowired
    private ItemInDao itemInDao;

    @Autowired
    private SummaryDao summaryDao;

    @Autowired
    private OutBillDao outBillDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Test
    public void test26() {
        String time = "2020-02-01 - 2020-03-31";
        String s = time.substring(0,10);
        String s1 = time.substring(13, 23);
        System.out.println(s);
        System.out.println(s1);
    }

    @Test
    public void test25() {
        //查找最新日期
        String latestTime = summaryDao.findLatestTime();
        List<Integer> AllGoodsID = summaryDao.findGoodsID();
        for (Integer goodsID : AllGoodsID) {
            //找出每个goodsID的时间最远的记录
            Summary longTimeSummary=summaryDao.findLongTimeSummary(goodsID);
            //查找最远月份与最新月相差多少个月
            String longTime = longTimeSummary.getTime() + "-1";
            Integer dateDiff = summaryDao.findDateDiff(longTime);//两个日期的差
            for (int i = 1; i <=dateDiff ; i++) {
                String dateAdd = summaryDao.dateAdd(i, longTime);
                //取前一个月的值
                String brforeMonth=summaryDao.findMonth(dateAdd+"-1");
                Summary beforeMonth = summaryDao.findBeforeMonth(goodsID, dateAdd+"-1");
                //在查找该月份有无数据
                Summary everyTimeSummary = summaryDao.findSummaryByGoodsIDAndTime(goodsID, dateAdd);
                if(everyTimeSummary==null){
                    Summary summary =new Summary();
                    summary.setGoodsID(beforeMonth.getGoodsID());
                    summary.setPreAmount(beforeMonth.getThisAmount());
                    summary.setPrePrice(beforeMonth.getThisPrice());
                    summary.setPreTotal(beforeMonth.getThisTotal());
                    summary.setInAmount(0);
                    summary.setInPrice(0.0);
                    summary.setInTotal(0.0);
                    summary.setOutAmount(0);
                    summary.setOutPrice(0.0);
                    summary.setOutTotal(0.0);
                    summary.setThisAmount(beforeMonth.getThisAmount());
                    summary.setThisPrice(beforeMonth.getThisPrice());
                    summary.setThisTotal(beforeMonth.getThisTotal());
                    summary.setTime(dateAdd);
                    summaryDao.insertSummary(summary);
                }
            }
        }

    }

    @Test
    public void test24(){
        List<Summary> allSummary = summaryDao.findAllSummary();
        for (Summary summary : allSummary) {
            System.out.println(summary);
        }
    }

    @Test
    public void test23() {
        List<RolePresent> allRolePresent = roleDao.findAllRolePresent(1, 10, 1);
        System.out.println(allRolePresent);
        int count = roleDao.findRolePresentCount(1);
        System.out.println(count);
    }

    @Test
    public void test22() {
        User user = userDao.findByLoginName("admin");
        System.out.println("roles" + user.getRoles());
        System.out.println(user);

        /*List<Role> roleByUserId = RoleDao.findRoleByUserId("3");
        System.out.println(roleByUserId);
        List<Permission> permissionByRoleId = permissionDao.findPermissionByRoleId("1");
        System.out.println(permissionByRoleId);*/

    }

    @Test
    public void test21() {

        Summary nearestSummaryByIdAndTime = summaryDao.findNearestSummaryByIdAndTime(24, "2019-12");
        System.out.println(nearestSummaryByIdAndTime);

    }

    @Test
    public void test20() {

        List<inBillShow> presentItemIn = inBillDao.findPresentItemIn("2019-12", 8);
        for (inBillShow inBillShow : presentItemIn) {

            System.out.println(inBillShow);
        }

    }

    @Test
    public void test19() {

        int re = summaryDao.countReload("re");
        System.out.println(re);

        List<SummartAndGoodsAndCategory> summartAndGoodsAndCategories = summaryDao.summaryAllCurrentdata(1, 5, "re");
        for (SummartAndGoodsAndCategory summartAndGoodsAndCategory : summartAndGoodsAndCategories) {
            System.out.println(summartAndGoodsAndCategory);
        }
    }

    @Test
    public void test18() {

        List<OutBillPresent> outBillPresents = outBillDao.ItemOutRecord(1, 5, 9);
        for (OutBillPresent outBillPresent : outBillPresents) {

            System.out.println(outBillPresent);
        }
    }

    @Test
    public void test17() {

        int count = inBillDao.CountItemInRecord(7);
        System.out.println(count);

        List<inBillShow> inBillShows = inBillDao.ItemInRecord(1, 10, 7);
        for (inBillShow inBillShow : inBillShows) {
            System.out.println(inBillShow);
        }
    }

    @Test
    public void test16() {

      /*  int i = inBillDao.countReload(null, null, null);
        System.out.println(i);
        int i1 = inBillDao.countReload("2019-11-01", "2019 - 12 - 01", null);
        System.out.println(i1);*/
        int i2 = inBillDao.countReload(null, null, "",1);
        System.out.println(i2);
    }

    @Test
    public void test15() {

        List<inBillShow> shows = inBillDao.inBillShowPage(1, 10, "", "", null,1);
        for (inBillShow show : shows) {
            System.out.println(show);
        }
    }

    @Test
    public void test14() {
        List<SummartAndGoodsAndCategory> summartAndGoodsAndCategories = summaryDao.summartyAllData();
        System.out.println(summartAndGoodsAndCategories);
    }


    @Test
    public void test13() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String date = "2019-12" + "-1";
            Date parse = simpleDateFormat.parse(date);
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String date = "2019-12" + "-1";
        Summary beforeMonth = summaryDao.findBeforeMonth(17, date);
        System.out.println(beforeMonth);
    }


    @Test
    public void test12() {

        int inBillByItemsID = itemInDao.findInBillByItemsID(343);
        System.out.println(inBillByItemsID);
    }

    @Test
    public void test11() {
        int summartAndGoodsAndCategories = summaryDao.summaryTotal("2019-11");
        System.out.println(summartAndGoodsAndCategories);
    }

    @Test
    public void test10() {
        List<GoodsAndCategoryAndItemsIn> itemsInList = itemInDao.getItemsInList(157);
        for (GoodsAndCategoryAndItemsIn itemIn : itemsInList) {
            System.out.println("itemIn" + itemIn);
        }
        System.out.println("itemsInList" + itemsInList);

        List<Goods> goodsBygoodsId = goodsDao.findGoodsBygoodsId(5);
        System.out.println("goodsBygoodsId" + goodsBygoodsId);
    }

    @Test
    public void test9() {
        List<GoodsAndCategoryAndItemsIn> itemsInList = itemInDao.getItemsInList(150);
        for (GoodsAndCategoryAndItemsIn itemIn : itemsInList) {
            System.out.println(itemIn);
        }
    }

    @Test
    public void test8() {

        List<Goods> goodsList = goodsDao.findGoodsByItemName("黑色签字笔");
        List<Category> categories = new ArrayList<>();
        for (Goods goods : goodsList) {
            System.out.println(goods);
            Integer categoryID = goods.getCategoryID();
            Category category = goodsDao.findCategoryNameByID(categoryID);
            categories.add(category);
            goods.setCategories(categories);
            System.out.println(goods.getCategories().get(0).getCategoryName());
        }

        System.out.println("goodslist:" + goodsList.get(0).getCategories().get(0).getCategoryName());
    }

    @Test
    public void test7() {
        List<Goods> allGoodsName = goodsDao.findAllGoodsName();
        for (Goods goods : allGoodsName) {
            System.out.println(goods);
        }
    }

    @Test
    public void test6() {
        int maxInBillID = inBillDao.findMaxInBillID();
        System.out.println(maxInBillID);
    }

    @Test
    public void test5() {
        InBill inBill = new InBill();
        inBill.setCheckStatus(2);
        inBill.setInBillID(150);
        itemInDao.UpdateCheckStatus(inBill);
    }

    @Test
    public void test4() {
        System.out.println(GetTime.getTime());
        System.out.println(GetTime.getTime());
        System.out.println(GetTime.getTime());
        System.out.println(GetTime.getTime());
        System.out.println(Integer.parseInt(null));
    }

    @Test
    public void test3() {
        List<inBillShow> inBillShows = inBillDao.inBillShowData();
        for (inBillShow inBillShow : inBillShows) {
            System.out.println(inBillShow);
        }
    }


    @Test
    public void test2() {
        List<ItemsShow> itemsInData = itemInDao.findItemsInData(149);
        for (ItemsShow itemsInDatum : itemsInData) {
            System.out.println(itemsInDatum);
        }
    }

    @Test
    public void test1() {
        InBill inBill = new InBill();
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
