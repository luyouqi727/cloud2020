package com.yog.springcloud.alibaba.service.impl;

import com.yog.springcloud.alibaba.dao.PaymentDao;
import com.yog.springcloud.alibaba.entities.Payment;
import com.yog.springcloud.alibaba.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
