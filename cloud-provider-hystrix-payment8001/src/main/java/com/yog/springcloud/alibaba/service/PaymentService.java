package com.yog.springcloud.alibaba.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程："+Thread.currentThread().getName()+" paymentInfo_OK,id："+id+"\t正常执行";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2")
    })

    public String paymentInfo_Timeout(Integer id){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int a = 11/0;
        return "线程："+Thread.currentThread().getName()+" paymentInfo_Timeout,id："+id+"\t 耗时一秒";
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程："+Thread.currentThread().getName()+" paymentInfo_TimeoutHandler,id："+id+"\t服务降级";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
            })
    public String paymentCircuitBreaker(Integer id){
        if(id<0){
            throw new RuntimeException("id不可为负数");
        }
        String s = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t调用成功，流水号"+s;
    }

    public String paymentCircuitBreakerFallback(Integer id){
        return "id 不能为负数,id："+id;
    }

}
