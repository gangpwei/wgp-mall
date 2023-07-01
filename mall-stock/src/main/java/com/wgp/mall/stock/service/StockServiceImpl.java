package com.wgp.mall.stock.service;

import com.wgp.mall.model.Stock;
import com.wgp.mall.service.StockService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gangpeng.wgp
 * @date 2023/6/30 下午11:48
 */
@DubboService(timeout = 3000, version = "1.0.0")
public class StockServiceImpl implements StockService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

    @Override
    public boolean reduceStock(String skuCode, Integer count) {
        LOGGER.info("reduceStock:" + skuCode);
        return true;
    }

    @Override
    public boolean addStock(String skuCode, Integer count) {
        return false;
    }

    @Override
    public Stock getStock(String skuCode) {
        return null;
    }
}
