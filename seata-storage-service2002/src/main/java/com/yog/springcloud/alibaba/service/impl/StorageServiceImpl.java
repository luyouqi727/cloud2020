package com.yog.springcloud.alibaba.service.impl;

import com.yog.springcloud.alibaba.dao.StorageDao;
import com.yog.springcloud.alibaba.service.StorageService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("---storage 库存减少开始");
        storageDao.decrease(productId,count);
        LOGGER.info("---storage 库存减少结束");
    }
}
