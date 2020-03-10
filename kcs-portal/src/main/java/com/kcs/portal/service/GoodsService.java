package com.kcs.portal.service;

import com.kcs.rest.pojo.AddOutBill;
import com.kcs.rest.pojo.Category;
import com.kcs.rest.pojo.Goods;

import java.util.List;

public interface GoodsService {
//    List<Goods> findGoodsByItemName(String itemName);

    List<Goods> findAllGoods();

    List<Goods> findAllGoodsName();

    List<Goods> findGoodsByGoodsID(String goodsID);
    //根据物品名查找物品，相同的品名只显示一条数据
    List<Goods> findGoodsByItemsName(String itemsName);

    //根据品名和规格，确定一条物品信息
    Goods findGoodsByItemsNameAndItemsType(Goods goods);

    //根据GoodsID查找物品
    Goods findGoodsByGoodsID(int goodsID);

    //根据日期，联合汇总表和物品表，查找物品
    List<Goods> findItemsNameUniqueByTime(String Time);

    //联合汇总表和物品表，查找在汇总表最后更新的物品id，再根据该id查找物品名称，物品名称再分组
    List<Goods> findItemsNameUnique();

    //物品类型》》物品id》》过滤汇总表最新数据（确定已入库且最新时间的物品》》过滤后的物品id》》物品名称》按名称分组
    List<String> findTheLastItemsNameByCategoryName(String categoryName);

    //根据物品名称，查询物品数据及汇总表的该物品的最新数据
    List<AddOutBill> findAddOutBillByItemsName(String itemsName);

    Category findCategoryNameByID(Integer categoryID);

    Integer addGoods(String goodsName, Integer categoryID, String goodsType, String goodsUnit);
}
