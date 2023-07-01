package com.wgp.mall.mq.service;

import javax.annotation.Resource;

import com.wgp.mall.mq.MqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author gangpeng.wgp
 * @date 2023/7/2 上午12:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaMsgServiceImplTest extends MqApplication {

    @Resource
    private KafkaMsgService kafkaMsgService;

    @Test
    public void sendMsg(){
        for (int i = 0; i < 10; i++) {

            kafkaMsgService.sendMsg("WGP-ORDER", String.valueOf(i), "订单支付" + i);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}