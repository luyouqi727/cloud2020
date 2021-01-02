package com.yog.springcloud.alibaba.controller;

import com.yog.springcloud.alibaba.domain.Order;
import com.yog.springcloud.alibaba.entities.CommonResult;
import com.yog.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        System.out.println(order);
        orderService.creat(order);
        return new CommonResult(200,"订单创建成功");
    }
}
