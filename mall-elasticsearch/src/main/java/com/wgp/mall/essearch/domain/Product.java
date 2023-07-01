package com.wgp.mall.essearch.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author : gangpeng.wgp
 * @date : 2023/6/18
 */
@Document(indexName = "pms", type = "product",shards = 1,replicas = 0)
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String productSn;
    private Long brandId;
    @Field(type = FieldType.Keyword)
    private String brandName;
    private Long productCategoryId;
    @Field(type = FieldType.Keyword)
    private String productCategoryName;
    private String pic;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String subTitle;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    @Field(type =FieldType.Nested)
    private List<ProductAttributeValue> attrValueList;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
