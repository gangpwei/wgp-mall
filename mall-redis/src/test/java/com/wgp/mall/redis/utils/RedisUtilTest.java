package com.wgp.mall.redis.utils;

import javax.annotation.Resource;

import com.wgp.mall.redis.service.ApplicationTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author gangpeng.wgp
 * @date 2023/7/1 œ¬ŒÁ7:12
 */
public class RedisUtilTest extends ApplicationTest {

    @Resource
    private RedisUtil redisUtil;




    @Test
    public void hasKey() {
    }

    @Test
    public void del() {
    }

    @Test
    public void get() {
    }

    @Test
    public void set() {
        redisUtil.set("wgp", "¿œŒ∫", 60);
        assertEquals("¿œŒ∫", redisUtil.get("wgp"));
    }

    @Test
    public void testSet() {
    }

    @Test
    public void incr() {
    }

    @Test
    public void decr() {
    }

    @Test
    public void hget() {
    }

    @Test
    public void hmget() {
    }

    @Test
    public void hmset() {
    }

    @Test
    public void testHmset() {
    }

    @Test
    public void hset() {
    }

    @Test
    public void testHset() {
    }

    @Test
    public void hdel() {
    }

    @Test
    public void hHasKey() {
    }

    @Test
    public void hincr() {
    }

    @Test
    public void hdecr() {
    }

    @Test
    public void sGet() {
    }

    @Test
    public void sHasKey() {
    }

    @Test
    public void sSet() {
    }

    @Test
    public void sSetAndTime() {
    }

    @Test
    public void sGetSetSize() {
    }

    @Test
    public void setRemove() {
    }

    @Test
    public void lGet() {
    }

    @Test
    public void lGetListSize() {
    }

    @Test
    public void lGetIndex() {
    }

    @Test
    public void lSet() {
    }

    @Test
    public void testLSet() {
    }

    @Test
    public void testLSet1() {
    }

    @Test
    public void testLSet2() {
    }

    @Test
    public void lUpdateIndex() {
    }

    @Test
    public void lRemove() {
    }
}