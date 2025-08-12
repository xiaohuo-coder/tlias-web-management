package com.atguigu.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProductServiceConfig {

    //将RestTemplate对象交给IOC容器管理，谁要用直接注入即可
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
