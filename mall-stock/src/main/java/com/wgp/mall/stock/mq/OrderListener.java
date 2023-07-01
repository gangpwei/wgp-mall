package com.wgp.mall.stock.mq;

import java.util.Optional;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import com.wgp.mall.model.Order;
import com.wgp.mall.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {

    @Resource
    private StockService stockService;

    @KafkaListener(topics = {"WGP-ORDER"})
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            Order order = JSON.parseObject(message.toString(), Order.class);
            log.info("收到下单消息:" + order);

            stockService.reduceStock(order.getSkuCode(), 1);
        }
    }

}