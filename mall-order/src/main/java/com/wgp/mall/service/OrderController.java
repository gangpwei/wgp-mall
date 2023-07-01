package com.wgp.mall.service;

import javax.annotation.Resource;

import com.wgp.mall.model.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gangpeng.wgp
 * @date 2023/7/1 上午12:15
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/add")
    public String add(){
        String skuCode = "123";
        Order o = new Order();
        o.setSkuCode(skuCode);
        Order order = orderService.createOrder(o);
        return String.valueOf(order.getOrderId());
    }


}
