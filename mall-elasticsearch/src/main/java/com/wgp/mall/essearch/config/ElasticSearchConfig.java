package com.wgp.mall.essearch.config;

/**
 * @author : gangpeng.wgp
 * @date : 2023/6/18
 */
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Value("${es.host}")
    private String esHost;

    @Value("${es.port}")
    private Integer esPort;

    @Bean
    public RestHighLevelClient restHighLevelClient(){

        RestClientBuilder builder=RestClient.builder(
                new HttpHost(esHost, esPort, "http"));
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "123456"));
        builder.setHttpClientConfigCallback(f -> f.setDefaultCredentialsProvider(credentialsProvider));
        RestHighLevelClient client = new RestHighLevelClient( builder);
        return client;


    }
}
