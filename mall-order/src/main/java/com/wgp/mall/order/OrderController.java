package com.wgp.mall.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import com.wgp.mall.model.Order;
import com.wgp.mall.request.OrderQueryRequest;
import com.wgp.mall.service.OrderService;
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
         o.setBuyerId(10L);
         o.setGmtCreate(new Date());
         o.setStatus("INIT");
         o.setPrice(BigDecimal.valueOf(1000.10));
         o.setSkuCode("sku_" + new Random().nextInt(1000));
         o.setSkuName("商品_" + new Random().nextInt(1000));
        o.setSkuCode(skuCode);
        Order order = orderService.createOrder(o);
        return order.toString();
    }

    @RequestMapping("/all")
    public String all(){
        OrderQueryRequest orderQueryRequest = new OrderQueryRequest();
        List<Order> orderList = orderService.queryOrder(orderQueryRequest);
        return orderList.toString();
    }


}
