package com.kcs.rest.service;

import com.kcs.rest.pojo.AddOutBill;
import com.kcs.rest.pojo.Category;
import com.kcs.rest.pojo.Goods;

import java.util.List;

public interface GoodsService {

    //根据id查找物品
    Goods findGoodsById(int id);

    //联合汇总表查找物品
    List<Goods> findGoodsInSummaryGoodsID();

    //根据物品名称查找物品
    List<Goods> findGoodsByItemName(String itemName);

    List<Goods> findAllGoods();

    List<Goods> findAllGoodsName();

    List<Goods> findGoodsBygoodsId(int id);

    //根据品名和规格查找物品
    Goods findGoodsByItemsNameAndItemsType(String itemsName,String itemsType);

    //根据日期，联合汇总表和物品表，查找物品
    List<Goods> findItemsNameUniqueByTime(String Time);

    //联合汇总表和物品表，查找在汇总表最后更新的物品id，再根据该id查找物品名称，物品名称再分组
    List<Goods> findItemsNameUnique();

    Category findCategoryNameByID(int categoryID);

    //根据物品类型，查询物品的详情及该物品对应汇总表最新数据
    List<AddOutBill> findAddOutBillByCategoryName(String categoryName);

    //物品类型》》物品id》》过滤汇总表最新数据（确定已入库且最新时间的物品》》过滤后的物品id》》物品名称》按名称分组
    List<String> findTheLastItemsNameByCategoryName(String categoryName);

    //根据物品名称，查询物品数据及汇总表的该物品的最新数据
    List<AddOutBill> findAddOutBillByItemsName(String itemsName);

    Integer addGoods(String goodsName, Integer categoryID, String goodsType, String goodsUnit,String image);

    List<Goods> goodsData(int front, int back);

    Goods showUpdateGoodsByID(int goodsID);

    Integer updateGoods(Goods goods);

    Integer delGoods(Goods goods);

    Integer countGoodsData();

    Goods findOtherGoods(Integer goodsID, String itemsName,String itemsType);
}
