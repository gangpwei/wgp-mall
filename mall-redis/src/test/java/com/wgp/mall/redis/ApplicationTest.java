package com.wgp.mall.redis;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : gangpeng.wgp
 * @date : 2023/6/18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest extends TestCase {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }
}
