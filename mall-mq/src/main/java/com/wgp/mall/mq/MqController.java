package com.wgp.mall.mq;

import javax.annotation.Resource;

import com.wgp.mall.mq.service.KafkaMsgService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gangpeng.wgp
 * @date 2023/7/1 上午12:15
 */
@RestController
@RequestMapping("/mq")
public class MqController {


    @Resource
    private KafkaMsgService kafkaMsgService;

    @RequestMapping("/send")
    public String send(){

        for (int i = 0; i < 10; i++) {

            kafkaMsgService.sendMsg("WGP-ORDER", String.valueOf(i), "订单支付" + i);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return "success";
    }


}
