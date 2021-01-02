package com.yog.springcloud.alibaba.controller;

import com.yog.springcloud.alibaba.entities.CommonResult;
import com.yog.springcloud.alibaba.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> map = new HashMap<>();
    static {
        map.put(1L,new Payment(1L,"aaa"));
        map.put(2L,new Payment(2L,"bbb"));
        map.put(3L,new Payment(3L,"ccc"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentCommonResult(@PathVariable("id") Long id){
        Payment payment = map.get(id);
        CommonResult<Payment> paymentCommonResult = new CommonResult<>(200, "from mysql,port: " + serverPort, payment);
        return paymentCommonResult;
    }
}
