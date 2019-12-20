package com.kcs.rest.dao;

import com.kcs.rest.pojo.AddOutBill;
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

    //根据物品类型，查询物品的详情及该物品对应汇总表最新数据
    List<AddOutBill> findAddOutBillByCategoryName(String categoryName);

    //物品类型》》物品id》》过滤汇总表最新数据（确定已入库且最新时间的物品》》过滤后的物品id》》物品名称》按名称分组
    List<String> findTheLastItemsNameByCategoryName(String categoryName);

    //根据物品名称，查询物品数据及汇总表的该物品的最新数据
    List<AddOutBill> findAddOutBillByItemsName(String itemsName);
}
