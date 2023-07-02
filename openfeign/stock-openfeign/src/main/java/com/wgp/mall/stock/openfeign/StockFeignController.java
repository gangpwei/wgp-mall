package com.wgp.mall.stock.openfeign;

import com.wgp.mall.common.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gangpeng.wgp
 * @date 2023/7/1 上午12:15
 */
@RestController
@RequestMapping("/stock")
@Log
@Slf4j
public class StockFeignController {

    @RequestMapping(value = "/reduce/{skuCode}/{count}", method = RequestMethod.GET)
    public boolean reduce(@PathVariable String skuCode, @PathVariable Integer count){
        log.info("feign reduceStock: {}, {}", skuCode, count);
        return true;
    }


}
