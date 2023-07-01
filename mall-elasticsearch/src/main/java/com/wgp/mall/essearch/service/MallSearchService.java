package com.wgp.mall.essearch.service;

import com.wgp.mall.essearch.vo.ESRequestParam;
import com.wgp.mall.essearch.vo.ESResponseResult;

/**
 * @author : gangpeng.wgp
 * @date : 2023/6/18
 */
public interface MallSearchService {


    /**
     * @param param 检索的所有参数
     * @return  返回检索的结果，里面包含页面需要的所有信息
     */
    ESResponseResult search(ESRequestParam param);


}


