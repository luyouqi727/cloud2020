package com.yog.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yog.springcloud.alibaba.dao")
public class MybatisConfig {
}
