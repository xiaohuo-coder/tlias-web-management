package com.atguigu.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class XTokenRequestInterceptor implements RequestInterceptor {

    /**
     * 请求拦截器
     * @param template 请求模板，封装请求的详细信息
     */
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("Hello 拦截器启动了");
        //自定义X-Token，使用UUID生成随机字符串
        template.header("X-Token", UUID.randomUUID().toString());

    }
}
