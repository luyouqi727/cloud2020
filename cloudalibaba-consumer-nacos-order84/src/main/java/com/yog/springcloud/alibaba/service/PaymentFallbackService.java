package com.yog.springcloud.alibaba.service;

import com.yog.springcloud.alibaba.entities.CommonResult;
import com.yog.springcloud.alibaba.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentCommonResult(Long id) {
        return new CommonResult<>(4444,"服务降级返回，paymentCommonResult",new Payment(id,null));
    }
}
