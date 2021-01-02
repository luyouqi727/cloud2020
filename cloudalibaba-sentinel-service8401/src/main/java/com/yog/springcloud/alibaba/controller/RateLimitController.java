package com.yog.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yog.springcloud.alibaba.entities.CommonResult;
import com.yog.springcloud.alibaba.entities.Payment;
import com.yog.springcloud.alibaba.myhander.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {


    @GetMapping("/byResource")
    @SentinelResource(value = "testHotKey",blockHandler = "ExceptionHandler")//value唯一即可
    public CommonResult byResource(){
        return new CommonResult<>(200,"按流量名称测试OK",new Payment(2020L,"serial001"));
    }

    public CommonResult ExceptionHandler(String p1, String p2, BlockException exception){
        return new CommonResult<>(444,exception.getClass().getCanonicalName()+"服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")//value唯一即可
    public CommonResult byUrl(){
        return new CommonResult<>(200,"按url测试OK",new Payment(2020L,"serial001"));
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")//value唯一即可
    public CommonResult customerBlockHandler(){
        return new CommonResult<>(200,"自定义测试",new Payment(2020L,"serial001"));
    }

}
