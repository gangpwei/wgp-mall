package com.wgp.mall.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author gangpeng.wgp
 * @date 2023/6/27 下午9:58
 */
@Data
//特别注意，不能踩坑：表名不能叫order，是mysql关键字。否则shardingsphere会启动失败
public class Torder {
    @TableId
    private Long orderId;
    private String status;
    private Date gmtCreate;
    private String skuCode;
    private String skuName;
    private BigDecimal price;
    private Long buyerId;

}
