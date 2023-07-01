
package com.wgp.mall.mq;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MqApplication.class, args);

        new CountDownLatch(1).await();
    }
}
