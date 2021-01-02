package com.yog.springcloud.alibaba.myhander;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yog.springcloud.alibaba.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handlerException1(BlockException exception){
        return new CommonResult<>(444,"自定义错误1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult<>(444,"自定义错误2");
    }
}
