package com.wgp.mall.service;

import com.wgp.mall.model.Stock;

/**
 * @author gangpeng.wgp
 * @date 2023/6/30 下午9:58
 */
public interface StockService {

    boolean reduceStock(String skuCode, Integer count);

    boolean addStock(String skuCode, Integer count);

    Stock getStock(String skuCode);

}
