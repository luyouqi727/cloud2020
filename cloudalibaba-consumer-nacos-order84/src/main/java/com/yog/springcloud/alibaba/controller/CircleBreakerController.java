package com.yog.springcloud.alibaba.controller;



import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yog.springcloud.alibaba.entities.CommonResult;
import com.yog.springcloud.alibaba.entities.Payment;
import com.yog.springcloud.alibaba.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    public static String SERVER_URL="http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")
    //@SentinelResource(value = "fallback",fallback = "handlerFallback")
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")
    //@SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler")
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",exceptionsToIgnore = IllegalArgumentException.class)
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult result = restTemplate.getForObject(SERVER_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4){
            throw new IllegalArgumentException("非法参数异常");
        }else if (result.getData()==null){
            throw new NullPointerException("没有对应记录");
        }

        return result;
    }

    public CommonResult handlerFallback(@PathVariable Long id,Throwable e){
        Payment payment = new Payment(id, "null");
        return new CommonResult(444,"兜底异常handlerFallback: "+e.getMessage(),payment);

    }
    public CommonResult blockHandler(@PathVariable Long id, BlockException e){
        Payment payment = new Payment(id, "null");
        return new CommonResult(444,"流量控制blockHandler: "+e.getMessage(),payment);

    }


    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentCommonResult(@PathVariable("id") Long id){
        return paymentService.paymentCommonResult(id);
    }
}
