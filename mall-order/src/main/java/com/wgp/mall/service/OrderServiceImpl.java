package com.wgp.mall.service;

import java.util.List;

import com.wgp.mall.model.Order;
import com.wgp.mall.request.OrderQueryRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author gangpeng.wgp
 * @date 2023/6/30 下午11:48
 */
@DubboService(timeout = 3000, version = "1.0.0")
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @DubboReference(version = "1.0.0")
    private StockService stockService;

    @Override
    public Order createOrder(Order order) {
        order.setOrderId(System.currentTimeMillis());
        stockService.reduceStock(order.getSkuCode(), 1);
        LOGGER.info("createOrder:" + order);
        return order;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<Order> queryOrder(OrderQueryRequest request) {
        return null;
    }
}
