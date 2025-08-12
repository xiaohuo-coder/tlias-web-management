package com.atguigu.gateway.predicate;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.cloud.gateway.handler.predicate.QueryRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.RoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

// 定义一个名为 VipRoutePredicateFactory 的类，它继承自 AbstractRoutePredicateFactory，
// 用于创建自定义的路由谓词工厂，泛型参数 VipRoutePredicateFactory.Config 表示该工厂使用的配置类
@Component
public class VipRoutePredicateFactory extends AbstractRoutePredicateFactory<VipRoutePredicateFactory.Config> {

    // 构造函数，用于初始化 VipRoutePredicateFactory 实例
    public VipRoutePredicateFactory() {
        // 调用父类 AbstractRoutePredicateFactory 的构造函数，传入配置类 Config.class
        // 这样父类就能知道该工厂使用的配置类类型
        super(Config.class);
    }

    // 重写父类的 apply 方法，该方法接收一个配置对象，返回一个 Predicate<ServerWebExchange> 实例
    // 这个 Predicate 用于判断请求是否满足路由条件
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        // 返回一个 GatewayPredicate 匿名内部类实例，GatewayPredicate 是一个函数式接口
        return new GatewayPredicate() {
            // 重写 test 方法，该方法接收一个 ServerWebExchange 对象，用于判断请求是否符合条件
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // 需要同时满足 localhost/search, q=haha, user=xiaohuo 这些条件
                // 从 ServerWebExchange 中获取 ServerHttpRequest 对象，该对象包含了请求的相关信息
                ServerHttpRequest request = serverWebExchange.getRequest();
                // 从请求的查询参数中获取指定参数名的第一个值，传入user(config.param)，获得xiaohuo（getFirst(config.param)）
                // config.param 是配置类中指定的参数名
                String first = request.getQueryParams().getFirst(config.param);
                // 判断获取到的值是否不为空且等于配置类中指定的值
                if (StringUtils.hasText(first) && first.equals(config.value)) {
                    // 如果满足条件，返回 true，表示请求符合路由规则
                    return true;
                } else {
                    // 如果不满足条件，返回 false，表示请求不符合路由规则
                    return false;
                }
            }
        };
    }

    // 重写 shortcutFieldOrder 方法，用于指定配置类中字段的快捷配置顺序
    // 这样在使用快捷配置语法时，Spring Cloud Gateway 可以按此顺序将配置值映射到配置类的字段上
    @Override
    public List<String> shortcutFieldOrder() {
        // 返回一个包含 "param" 和 "value" 的列表，表示快捷配置时参数的顺序
        return Arrays.asList("param", "value");
    }

    // 使用 @Validated 注解，表示该类的属性需要进行验证
    @Validated
    // 定义一个静态内部类 Config，用于存储 VipRoutePredicateFactory 的配置信息
    public static class Config {
        // 使用 @NotEmpty 注解，确保 param 字段不为空
        @NotEmpty
        // 定义一个私有字符串类型的 param 字段，用于存储请求查询参数的名称
        private String param;
        // 使用 @NotEmpty 注解，确保 value 字段不为空
        @NotEmpty
        // 定义一个私有字符串类型的 value 字段，用于存储请求查询参数需要匹配的值
        private String value;

        // 获取 param 字段的值，使用 @NotEmpty 注解确保返回值不为空
        public @NotEmpty String getParam() {
            return param;
        }

        // 设置 param 字段的值，使用 @NotEmpty 注解确保传入的参数不为空
        public void setParam(@NotEmpty String param) {
            this.param = param;
        }

        // 获取 value 字段的值，使用 @NotEmpty 注解确保返回值不为空
        public @NotEmpty String getValue() {
            return value;
        }

        // 设置 value 字段的值，使用 @NotEmpty 注解确保传入的参数不为空
        public void setValue(@NotEmpty String value) {
            this.value = value;
        }
    }
}