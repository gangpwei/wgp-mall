package com.wgp.mall.order.openfeign;

import javax.annotation.Resource;

import com.wgp.mall.openfeign.service.StockFeignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : gangpeng.wgp
 * @date : 2023/7/2
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private StockFeignService stockFeignService;

    /**
     * url: http://127.0.0.1:8022/order/add
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        System.out.println("下单成功!");
        boolean s = stockFeignService.reduce("12345", 88);
        return "库存扣减结果：" + s;
    }
}
