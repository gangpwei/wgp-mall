

package com.wgp.mall.stock.openfeign;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class StockFeignApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(StockFeignApplication.class, args);
        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
