package com.atguigu.order.config;

import feign.Logger;
import feign.RetryableException;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderServiceConfig {

    //将RestTemplate对象交给IOC容器管理，谁要用直接注入即可
    @LoadBalanced //注解式负载均衡功能
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    //重试机制
    //@Bean
    Retryer retryer() {
        return new Retryer.Default();
    }
}
