package com.yog.springcloud.alibaba.lib;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    public ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
