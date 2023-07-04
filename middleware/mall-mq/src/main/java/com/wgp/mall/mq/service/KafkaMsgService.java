package com.wgp.mall.mq.service;

/**
 * @author gangpeng.wgp
 * @date 2023/7/1 下午9:54
 */
public interface KafkaMsgService {
    boolean sendMsg(String topic, String key, String msg);
}
