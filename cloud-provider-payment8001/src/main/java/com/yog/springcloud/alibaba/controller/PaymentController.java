package com.yog.springcloud.alibaba.controller;

import com.yog.springcloud.alibaba.service.PaymentService;
import com.yog.springcloud.alibaba.entities.CommonResult;
import com.yog.springcloud.alibaba.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult creat(@RequestBody Payment payment){
        Integer result = paymentService.create(payment);
        System.out.println("******插入数据："+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功-"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        System.out.println("******查询结果："+payment);

        if(payment!=null){
            return new CommonResult(200,"查询成功-"+serverPort,payment);
        }else {
            return new CommonResult(444,"没有id为"+id+"的记录",null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }


    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "zipkin,abcdefghij";
    }
}
