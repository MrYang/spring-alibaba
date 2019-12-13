package com.zz.nacos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "sentinel-service", fallback = FeignErrorSentinelService.class)
public interface FeignSentinelService {

    @RequestMapping("/limit/url")
    String limitUrl();
}
