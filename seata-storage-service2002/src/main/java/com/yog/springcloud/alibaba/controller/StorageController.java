package com.yog.springcloud.alibaba.controller;

import com.yog.springcloud.alibaba.entities.CommonResult;
import com.yog.springcloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;


    //扣减库存
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(@RequestParam("productId")Long productId,@RequestParam("count") Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功！");
    }
}