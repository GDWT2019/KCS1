package com.kcs.rest.dao;

import com.kcs.rest.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDao {

    //根据id查找物品
    Goods findGoodsById(int id);

    //联合汇总表查找物品
    List<Goods> findGoodsInSummaryGoodsID();

    //根据物品名称查找物品
    List<Goods> findGoodsByItemName(String itemName);

    //根据品名和规格查找物品
    Goods findGoodsByItemsNameAndItemsType(@Param("ItemsName") String itemsName, @Param("ItemsType") String itemsType);

    //根据日期，联合汇总表和物品表，查找物品
    List<Goods> findItemsNameUniqueByTime(String Time);

    //联合汇总表和物品表，查找在汇总表最后更新的物品id，再根据该id查找物品名称，物品名称再分组
    List<Goods> findItemsNameUnique();

    List<Goods> findAllGoods();

    List<Goods> findGoodsBygoodsId(int id);
}
