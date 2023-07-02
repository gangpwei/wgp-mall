package com.wgp.mall.stock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * 从Nacos读配置
 * @author gangpeng.wgp
 * @date 2023/7/2 上午9:28
 */
@Service
@RefreshScope
public class MallConfig {

    @Value("${boss.name}")
    private  String bossName;

    /**
     * 增加get 方法，在外面才能读到变量值
     *
     * @return
     */
    public String getBossName() {
        return bossName;
    }
}
