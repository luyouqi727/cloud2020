package com.yog.springcloud.alibaba.service.impl;

import com.yog.springcloud.alibaba.dao.AccountDao;
import com.yog.springcloud.alibaba.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("---account 账户余额减少开始");
        accountDao.decrease(userId,money);
        LOGGER.info("---account 账户余额减少结束");
    }
}
