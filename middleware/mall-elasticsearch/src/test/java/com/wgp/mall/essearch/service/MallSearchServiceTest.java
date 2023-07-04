package com.wgp.mall.essearch.service;

import java.util.Objects;

import javax.annotation.Resource;

import com.wgp.mall.essearch.vo.ESRequestParam;
import com.wgp.mall.essearch.vo.ESResponseResult;
import org.junit.Test;

/**
 * @author gangpeng.wgp
 * @date 2023/6/18 下午12:38
 */

public class MallSearchServiceTest extends ApplicationTest {

    @Resource
    private MallSearchService mallSearchService;


    @Test
    public void testSearch() {
        ESRequestParam param = new ESRequestParam();
        param.setKeyword("手机");
        param.setPageNum(1);
        param.setPriceMin(2000);
        param.setPriceMax(5000);

        ESResponseResult result = mallSearchService.search(param);
        assertTrue(Objects.nonNull(result));
        assertTrue(Objects.nonNull(result.getProducts()));
        result.getProducts().forEach(System.out::println);

        assertTrue(Objects.nonNull(result.getBrands()));
        result.getBrands().forEach(System.out::println);

        assertTrue(Objects.nonNull(result.getAttrs()));
        result.getAttrs().forEach(System.out::println);
    }
}