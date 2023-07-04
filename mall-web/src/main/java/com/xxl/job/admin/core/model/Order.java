package com.xxl.job.admin.core.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @author gangpeng.wgp
 * @date 2023/6/27 下午9:58
 */
@Data
public class Order {

    private Long orderId;
    private String status;
    private Date gmtCreate;
    private String skuCode;
    private String skuName;
    private BigDecimal price;
    private Long buyerId;

}
