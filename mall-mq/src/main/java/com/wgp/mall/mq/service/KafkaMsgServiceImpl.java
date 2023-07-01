package com.wgp.mall.mq.service;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author gangpeng.wgp
 * @date 2023/6/30 下午11:48
 */
@Service
@Slf4j
public class KafkaMsgServiceImpl implements KafkaMsgService {

    @Resource
    private KafkaTemplate<String, String> kafka;

    @Override
    public boolean sendMsg(String topic, String key, String msg) {
        log.info("发送kafka消息:" + msg);
        kafka.send(topic, key, msg );
        return true;
    }


}
