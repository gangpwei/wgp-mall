package com.wgp.mall.openfeign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author : gangpeng.wgp
 * @date : 2023/7/2
 */
//@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }


    /**
     * 修改契约配置，支持Feign原生的注解
     * @return
    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }
     */
/**
 * 超时时间配置
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }
 */

    /**
     * 自定义拦截器
     * @return
    @Bean
    public FeignAuthRequestInterceptor feignAuthRequestInterceptor(){
        return new FeignAuthRequestInterceptor();
    }
     */
}
