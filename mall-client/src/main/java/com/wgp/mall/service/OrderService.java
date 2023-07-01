package com.wgp.mall.service;

import java.util.List;

import com.wgp.mall.model.Order;
import com.wgp.mall.request.OrderQueryRequest;

/**
 * @author gangpeng.wgp
 * @date 2023/6/30 下午9:58
 */
public interface OrderService {

    Order createOrder(Order order);

    Order createOrderAndAsyncReduceStock(Order order);

    Order getOrderById(Long orderId);

    List<Order> queryOrder(OrderQueryRequest request);
}
