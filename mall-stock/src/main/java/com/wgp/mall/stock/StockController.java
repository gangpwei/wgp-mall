package com.wgp.mall.stock;

import javax.annotation.Resource;

import com.wgp.mall.stock.config.MallConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gangpeng.wgp
 * @date 2023/7/1 上午12:15
 */
@RestController
@RequestMapping("/stock")
@RefreshScope
public class StockController {

    @Resource
    private MallConfig mallConfig;

    @RequestMapping("/config")
    public String config(){

        return mallConfig.getBossName();
    }

    @Value("${boss.name}")
    public String name;

    @RequestMapping("/show")
    public String show(){
        return name;
    }



}
