package com.wgp.mall.openfeign.service;

import com.wgp.mall.openfeign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : gangpeng.wgp
 * @date : 2023/7/2
 */

@FeignClient(name="stock-service",path ="/stock",configuration = FeignConfig.class)
public interface StockFeignService {

    // 声明需要调用的rest接口对应的方法
    @RequestMapping(value = "/reduce/{skuCode}/{count}", method = RequestMethod.GET)
    boolean reduce(@PathVariable String skuCode, @PathVariable Integer count);
}

