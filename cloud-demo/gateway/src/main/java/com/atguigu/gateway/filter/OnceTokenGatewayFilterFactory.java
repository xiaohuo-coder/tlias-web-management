package com.atguigu.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@Slf4j
public class OnceTokenGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //每次响应之前，添加一个一次性令牌，支持UUID，JWT等各种格式
                log.info("自定义过滤器...name={},value={}", config.getName(), config.getValue());
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    ServerHttpResponse response = exchange.getResponse();
                    HttpHeaders headers = response.getHeaders();
                    String value = config.getValue();
                    if ("uuid".equals(value)){
                        value = UUID.randomUUID().toString();
                    }

                    if ("jwt".equals(value)){
                        value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjE1MTYyMzkwODIsImF1ZCI6Imh0dHBzOi8vZXhhbXBsZS5jb20iLCJpc3MiOiJ0b2tlbi1pc3N1ZXIifQ.4XnRjK9QJ2QlJd4hHl5z9l4oO7c2y8n0j5v6c7d8e9w0v4t2E";
                    }
                    headers.add(config.getName(), value); //X-Response-Token, uuid
                }));
            }
        };
    }
}
