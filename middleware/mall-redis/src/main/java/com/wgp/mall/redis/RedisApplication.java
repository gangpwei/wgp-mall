package com.wgp.mall.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : gangpeng.wgp
 * @date : 2023/6/18
 */
@SpringBootApplication(scanBasePackages = "com.wgp.mall")
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
