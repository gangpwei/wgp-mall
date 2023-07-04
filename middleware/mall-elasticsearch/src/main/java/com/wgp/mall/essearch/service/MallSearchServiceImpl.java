package com.wgp.mall.essearch.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import com.wgp.mall.essearch.domain.Product;
import com.wgp.mall.essearch.vo.ESRequestParam;
import com.wgp.mall.essearch.vo.ESResponseResult;
import com.wgp.mall.essearch.vo.ESResponseResult.AttrVo;
import com.wgp.mall.essearch.vo.ESResponseResult.BrandVo;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

/**
 * @author gangpeng.wgp
 * @date 2023/6/18 下午12:13
 */
@Service
public class MallSearchServiceImpl implements MallSearchService {

    @Resource
    private RestHighLevelClient client;

    @Override
    public ESResponseResult search(ESRequestParam param) {
        SearchRequest searchRequest = buildRequest(param);

        return getEsResponseResult(searchRequest, param);
    }

    private ESResponseResult getEsResponseResult(SearchRequest searchRequest, ESRequestParam param) {
        ESResponseResult result = new ESResponseResult();
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits searchHits = searchResponse.getHits();
            if (searchHits == null) {
                return result;
            }

            List<Product> productList = new ArrayList<>();
            for (SearchHit hit : searchHits.getHits()) {
                Product product = JSON.parseObject(hit.getSourceAsString(), Product.class);
                productList.add(product);
            }
            result.setProducts(productList);

            result.setTotal(searchHits.getTotalHits().value);
            result.setPageNum(param.getPageNum());

            Aggregations aggregations = searchResponse.getAggregations();
            if (Objects.nonNull(aggregations)) {
                ParsedLongTerms brandIdAgg = aggregations.get("brandId_aggs");
                List<BrandVo> brands = new ArrayList<>();
                for (Bucket bucket : brandIdAgg.getBuckets()) {
                    ParsedStringTerms brandNameAgg = bucket.getAggregations().get("brandName_aggs");
                    String brandName = brandNameAgg.getBuckets().get(0).getKeyAsString();
                    BrandVo brand = new BrandVo();
                    brand.setBrandId(bucket.getKeyAsNumber().longValue());
                    brand.setBrandName(brandName);
                    brands.add(brand);
                }
                result.setBrands(brands);

                ParsedNested attrAgg = aggregations.get("attrs_aggs");
                ParsedLongTerms attrIdAgg = attrAgg.getAggregations().get("attrId_aggs");
                List<AttrVo> attrList = new ArrayList<>();
                for (Bucket bucket : attrIdAgg.getBuckets()) {
                    AttrVo attr = new AttrVo();
                    ParsedStringTerms attrNameAgg = bucket.getAggregations().get("attrName_aggs");
                    String attrName = attrNameAgg.getBuckets().get(0).getKeyAsString();

                    ParsedStringTerms attrValueAgg = bucket.getAggregations().get("attrValue_aggs");
                    List<String> attrValueList = attrValueAgg.getBuckets().stream().map(item -> item.getKeyAsString()).collect(Collectors.toList());
                    attr.setAttrId(bucket.getKeyAsNumber().longValue());
                    attr.setAttrName(attrName);
                    attr.setAttrValue(attrValueList);
                    attrList.add(attr);
                }
                result.setAttrs(attrList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private SearchRequest buildRequest(ESRequestParam param) {
        SearchRequest searchRequest = new SearchRequest("product_db");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", param.getKeyword());
        boolQueryBuilder.filter(matchQueryBuilder);

        if (Objects.nonNull(param.getHasStock())) {
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("hasStock", param.getHasStock() == 1 ? "true" : "false");
            boolQueryBuilder.filter(termQueryBuilder);
        }

        //根据价格过滤
        if (Objects.nonNull(param.getPriceMax()) && Objects.nonNull(param.getPriceMin())) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price");
            rangeQueryBuilder.gte(param.getPriceMin());
            rangeQueryBuilder.lte(param.getPriceMax());
            boolQueryBuilder.filter(rangeQueryBuilder);
        }

        sourceBuilder.query(boolQueryBuilder);

        TermsAggregationBuilder brandAgg = AggregationBuilders.terms("brandId_aggs");
        brandAgg.field("brandId").size(50);

        TermsAggregationBuilder brandNameAgg = AggregationBuilders.terms("brandName_aggs");
        brandNameAgg.field("brandName").size(50);
        brandAgg.subAggregation(brandNameAgg);

        sourceBuilder.aggregation(brandAgg);

        NestedAggregationBuilder attrsAgg = AggregationBuilders.nested("attrs_aggs", "attrs");


        TermsAggregationBuilder attrIdAgg = AggregationBuilders.terms("attrId_aggs");
        attrIdAgg.field("attrs.attrId");
        attrIdAgg.size(50);
        attrsAgg.subAggregation(attrIdAgg);
        sourceBuilder.aggregation(attrsAgg);

        TermsAggregationBuilder attrNameAgg = AggregationBuilders.terms("attrName_aggs");
        attrNameAgg.field("attrs.attrName");
        attrNameAgg.size(50);
        attrIdAgg.subAggregation(attrNameAgg);

        TermsAggregationBuilder attrValueAgg = AggregationBuilders.terms("attrValue_aggs");
        attrValueAgg.field("attrs.attrValue");
        attrValueAgg.size(50);
        attrIdAgg.subAggregation(attrValueAgg);


        sourceBuilder.from((param.getPageNum() - 1) * 50);
        sourceBuilder.timeout(new TimeValue(10, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);

        sourceBuilder.sort("salecount", SortOrder.DESC);

        System.out.println("构建的DSL语句 :" + sourceBuilder.toString());

        return searchRequest;
    }
}
