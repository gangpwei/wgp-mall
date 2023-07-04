package com.wgp.mall.redis;

import javax.annotation.Resource;

import com.wgp.mall.redis.service.impl.CacheService;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gangpeng.wgp
 * @date 2023/7/2 下午3:03
 */
@RestController
@RequestMapping("/")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RedisController {
    @Resource
    private CacheService cacheService;

    @RequestMapping("/get/{key}")
    public String get(@PathVariable String key){
        if(!cacheService.hasKey(key)){
            cacheService.add(key, "这是一个默认值");
        }
        return cacheService.get(key);
    }

}
