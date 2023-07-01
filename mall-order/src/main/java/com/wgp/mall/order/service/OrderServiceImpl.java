package com.wgp.mall.order.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.wgp.mall.model.Order;
import com.wgp.mall.order.entity.Torder;
import com.wgp.mall.order.mapper.OrderMapper;
import com.wgp.mall.request.OrderQueryRequest;
import com.wgp.mall.service.OrderService;
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

    //@DubboReference(version = "1.0.0")
    //private StockService stockService;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public Order createOrder(Order order) {

        orderMapper.insert(convertToDo(order));
        //stockService.reduceStock(order.getSkuCode(), 1);
        LOGGER.info("createOrder:" + order);
        return order;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<Order> queryOrder(OrderQueryRequest request) {
        QueryWrapper<Torder> wrapper = new QueryWrapper<>();
        List<Torder>  torderList = orderMapper.selectList(wrapper);
        return Optional.ofNullable(torderList).orElse(Lists.newArrayList()).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Order convertToDto(Torder t) {
        Order o = new Order();
        o.setOrderId(t.getOrderId());
        o.setSkuCode(t.getSkuCode());
        o.setBuyerId(t.getBuyerId());
        o.setSkuName(t.getSkuName());
        o.setGmtCreate(t.getGmtCreate());
        o.setPrice(t.getPrice());
        o.setStatus(t.getStatus());
        return o;
    }

    private Torder convertToDo(Order t) {
        Torder o = new Torder();
        o.setOrderId(t.getOrderId());
        o.setSkuCode(t.getSkuCode());
        o.setBuyerId(t.getBuyerId());
        o.setSkuName(t.getSkuName());
        o.setGmtCreate(new Date());
        o.setPrice(t.getPrice());
        o.setStatus(t.getStatus());
        return o;
    }
}
