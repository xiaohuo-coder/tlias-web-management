package com.atguigu.order.service.impl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.order.bean.Order;
import com.atguigu.order.feign.ProductFeignClient;
import com.atguigu.order.service.OrderService;
import com.atguigu.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    ProductFeignClient productFeignClient;

    @SentinelResource(value = "createOrder", blockHandler = "createOrderFallback")
    @Override
    public Order createOrder(Long productId, Long userId) {

        //远程调用商品微服务，获取商品信息
        //Product product = getProductFromRemoteWithLoadbalancerAnnotation(productId);

        Product product = productFeignClient.getProductById(productId);

        log.info("商品信息：{}",product);

        Order order = new Order();

        order.setId(1L);

//        try {
//            SphU.entry("hahahLoL");
//        } catch (BlockException e) {
//            throw new RuntimeException(e);
//        }

        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("zhangsan");
        order.setAddress("尚硅谷");
        order.setProductList(Arrays.asList(product));

        return order;
    }

    //兜底回调方法
    public Order createOrderFallback(Long productId, Long userId, BlockException e) {
        Order order = new Order();
        order.setId(0L);
        order.setTotalAmount(new BigDecimal("0"));
        order.setUserId(userId);
        order.setNickName("未知用户");
        order.setAddress("异常信息："+ e.getClass());

        log.error("创建订单失败，原因：{}",e.getMessage());
        return order;
    }


        /**
         * 注解式负载均衡
         */
    private Product getProductFromRemoteWithLoadbalancerAnnotation(Long productId) {

        String url = "http://service-product/product/"+ productId;

        //给远程发送请求，获取返回的json
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }

    /**
     * 负载均衡
     */
    private Product getProductFromRemoteWithLoadbalancer(Long productId) {
        //使用负载均衡器获取机器
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        //拼接请求远程调用地址
        String url = "http://"+ choose.getHost() + ":" + choose.getPort() + "/product/"+ productId;
        log.info("远程请求：{}",url);
        //给远程发送请求，获取返回的json
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }

    private Product getProductFromRemote(Long productId) {
        //获取所有机器的IP和端口
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        //随机选择一台机器，比如第一个
        ServiceInstance instance = instances.get(0);
        //拼接请求远程调用地址
        String url = "http://"+ instance.getHost() + ":" + instance.getPort() + "/product/"+ productId;
        log.info("远程请求：{}",url);
        //给远程发送请求，获取返回的json
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }
}
