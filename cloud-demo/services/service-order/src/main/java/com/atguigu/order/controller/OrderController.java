package com.atguigu.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.order.bean.Order;
import com.atguigu.order.properties.OrderProperties;
import com.atguigu.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope
//@RequestMapping("/api/order")
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    OrderProperties orderProperties;

//    @Value("${order.timeout}")
//    String timeout;
//    @Value("${order.auto-confirm}")
//    String autoConfirm;

    @GetMapping("/config")
    public String config(){
        return "timeout: " + orderProperties.getTimeout()
                + ", autoConfirm: " + orderProperties.getAutoConfirm()
                + ", dbUrl: " + orderProperties.getDbUrl();
    }

    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        return order;
    }

    @SentinelResource(value = "seckill-order", blockHandler = "seckillFallback")
    @GetMapping("/seckill")
    public Order seckill(@RequestParam(value = "userId",defaultValue = "888") Long userId,
                             @RequestParam(value = "productId",defaultValue = "1000") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    public Order seckillFallback(Long userId, Long productId, BlockException e) {
        System.out.println("seckillFallback被調用...");
        Order order = new Order();
        order.setId(productId);
        order.setUserId(userId);
        order.setAddress("異常信息"+ e.getClass());
        return order;
    }

    @GetMapping("/writeDb")
    public String writeDb(){
        return "wirteDb success...";
    }

    @GetMapping("/readDb")
    public String readDb(){
        log.info("readDb aaa...");
        return "readDb success...";
    }
}
