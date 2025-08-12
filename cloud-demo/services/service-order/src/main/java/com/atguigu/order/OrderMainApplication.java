package com.atguigu.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class OrderMainApplication {

    public static void main(String[] args) {

        SpringApplication.run(OrderMainApplication.class, args);
    }

    //项目启动就监听配置文件的变化
    //获取变化的配置文件信息，重新加载到IOC容器中
    //通过邮件通知给开发人员
    @Bean
    ApplicationRunner applicationRunner(NacosConfigManager nacosConfigManager) {
        /* return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                System.out.println("项目启动了");
            }
        } */

        //lambda表达式简写
        return args -> {
            ConfigService configService = nacosConfigManager.getConfigService();
            configService.addListener("order.properties", "DEFAULT_GROUP", new Listener() {
                @Override
                public Executor getExecutor() {
                    return Executors.newFixedThreadPool(4);
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("变化的配置信息："+configInfo);
                    System.out.println("进行邮件通知");
                }
            });
            System.out.println("项目启动了");
        };
    };
}
