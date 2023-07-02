package com.wgp.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : gangpeng.wgp
 * @date : 2023/7/2
 */
@SpringBootApplication(scanBasePackages = "com.wgp.mall")
@EnableFeignClients
public class OrderFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignApplication.class,args);
    }

}
