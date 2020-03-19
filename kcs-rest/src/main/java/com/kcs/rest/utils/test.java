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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
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

    @Autowired
    private OutBillDao outBillDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private OutBillPresentDao outBillPresentDao;

    @Test
    public void test31(){
        List<OutBillPresent> allOutBillPresent =  outBillPresentDao.findAllOutBillPresent(1, 10, null, null, null, 0, 0);
        for (OutBillPresent outBillPresent : allOutBillPresent) {
            System.out.println(outBillPresent);
        }
    }

    @Test
    public void test30(){
        boolean b =true;
        if(!b){//如果B是false
            System.out.println(b);
            System.out.println(1);
        }else {
            System.out.println(b);
            System.out.println(2);
        }
    }

    @Test
    public void test29(){
        ItemIn ite = itemInDao.findItemsInByItemsID(200);
        Integer itemNum = ite.getItemNum();
        System.out.println(ite);
        System.out.println(itemNum);
    }

    @Test
    public void test28(){
        String s = test27();
        System.out.println(s);
    }


    public String test27(){
        List<Integer> list =new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(0);
        list.add(1);
        list.add(2);
        for (Integer integer : list) {
            if(integer<0){
                return "小于0";
            }
        }
        return "没有小于0";

    }

    @Test
    public void test26(){
        Summary summary = summaryDao.findThisMonthInAmountByGoodsID(6, "2020-03");
        Integer inAmount = summary.getInAmount();
        Integer allInAmout = summaryDao.findAllInAmout(6);
        Integer allOutAmout = summaryDao.findAllOutAmout(6);
        Integer total=allInAmout+3-inAmount-allOutAmout;
        System.out.println("total:"+total);
        System.out.println();
        if((allInAmout+3-inAmount-allOutAmout)>=0){
            System.out.println("入库大于出库");
        }else {
            System.out.println("入库小于出库");
        }
    }

    @Test
    public void test25(){
       inBillDao.updateInBillAlltotalByID(5330f,104);
    }

    @Test
    public void test24(){
        Float allTotal = itemInDao.findAllTotal(104);
        System.out.println(allTotal);
    }

    @Test
    public void test23(){
        List<RolePresent> allRolePresent = roleDao.findAllRolePresent(1, 10, 1);
        System.out.println(allRolePresent);
        int count = roleDao.findRolePresentCount(1);
        System.out.println(count);
    }

    @Test
    public void test22(){
        User user = userDao.findByLoginName("admin");
        System.out.println("roles"+user.getRoles());
        System.out.println(user);

        /*List<Role> roleByUserId = RoleDao.findRoleByUserId("3");
        System.out.println(roleByUserId);
        List<Permission> permissionByRoleId = permissionDao.findPermissionByRoleId("1");
        System.out.println(permissionByRoleId);*/

    }

    @Test
    public void test21(){

        Summary nearestSummaryByIdAndTime = summaryDao.findNearestSummaryByIdAndTime(24, "2019-12");
        System.out.println(nearestSummaryByIdAndTime);

    }

    @Test
    public void test20(){

        List<inBillShow> presentItemIn = inBillDao.findPresentItemIn("2019-12", 8);
        for (inBillShow inBillShow : presentItemIn) {

            System.out.println(inBillShow);
        }

    }

    @Test
    public void test19(){

       /* List<SummartAndGoodsAndCategory> list = summaryDao.summaryAllCurrentdata(1, 10, "2020-02", "2020-03", null);
        for (SummartAndGoodsAndCategory summartAndGoodsAndCategory : list) {
            System.out.println(summartAndGoodsAndCategory);
        }*/
        int i = summaryDao.countReload(null,null, null);
        System.out.println(i);
    }

    @Test
    public void test18(){

        List<OutBillPresent> outBillPresents = outBillDao.ItemOutRecord(1, 5, 9);
        for (OutBillPresent outBillPresent : outBillPresents) {

            System.out.println(outBillPresent);
        }
    }

    @Test
    public void test17(){

        int count = inBillDao.CountItemInRecord(7);
        System.out.println(count);

        List<inBillShow> inBillShows = inBillDao.ItemInRecord(1, 10, 7);
        for (inBillShow inBillShow : inBillShows) {
            System.out.println(inBillShow);
        }
    }

/*    @Test
    public void test16(){

        int i = inBillDao.countReload(null, null, null);
        System.out.println(i);
        int i1 = inBillDao.countReload("2019-11-01", "2019 - 12 - 01", null);
        System.out.println(i1);
        int i2 = inBillDao.countReload(null, null, "小");
        System.out.println(i2);
    }*/

    /*@Test
    public void test15(){

        List<inBillShow> shows = inBillDao.inBillShowPage(1, 10, "2019-11-01", "2019-12-05", null);
        System.out.println(shows);
    }
*/
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
