package com.wgp.mall.gayteway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 访问请求
 * 全局过滤器
 * @author : gangpeng.wgp
 * @date : 2023/7/2
 */
@Component
public class AccessFilter implements GlobalFilter {
    Logger log= LoggerFactory.getLogger(this.getClass());

    /**
     * 注意：不是所有请求都会打印，如果前面有鉴权过滤器，鉴权失败的都会直接返回，不会走到这里
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info(exchange.getRequest().getPath().value());
        return chain.filter(exchange);
    }
}
