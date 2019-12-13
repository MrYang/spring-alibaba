package com.zz.nacos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nacos-server", fallback = FeignErrorHelloService.class)
public interface FeignNacosServerService {

    @RequestMapping("/hello")
    String hello(@RequestParam("name") String name);
}
