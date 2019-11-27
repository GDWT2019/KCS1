package com.kcs.rest.dao;

import com.kcs.rest.pojo.Goods;

public interface GoodsDao {
    Goods findGoodsById(int id);
}
