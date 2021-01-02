package com.yog.springcloud.alibaba.service.impl;

import com.yog.springcloud.alibaba.dao.OrderDao;
import com.yog.springcloud.alibaba.domain.Order;
import com.yog.springcloud.alibaba.service.AccountService;
import com.yog.springcloud.alibaba.service.OrderService;
import com.yog.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;



    @Override
    @GlobalTransactional(name = "fsp_tx_group",rollbackFor = Exception.class)
    public void creat(Order order) {
        log.info("---开始新建订单");
        orderDao.create(order);

        log.info("---订单微服务请求账户扣钱开始");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("---订单微服务请求账户扣钱结束");

        log.info("---订单微服务请求减少库存开始");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("---订单微服务请求减少库存结束");

        log.info("---修改订单状态0-->1 开始");
        orderDao.update(order.getUserId(),0);
        log.info("---修改订单状态0-->1 结束");

        log.info("---创建订单结束");

    }
}
