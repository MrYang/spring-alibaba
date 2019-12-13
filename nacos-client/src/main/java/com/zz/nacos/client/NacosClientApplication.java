package com.zz.nacos.client;

import com.zz.dubbo.service.DubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class NacosClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class, args);
    }

    @Autowired
    private FeignNacosServerService feignNacosServerService;

    @Autowired
    private FeignSentinelService feignSentinelService;

    @Reference
    private DubboService dubboService;

    @RequestMapping("hello")
    public String hello(String name) {
        return feignNacosServerService.hello(name);
    }

    @RequestMapping("limit/url")
    public String limitUrl() {
        return feignSentinelService.limitUrl();
    }

    @RequestMapping("dubbo")
    public String dubbo(String name) {
        return dubboService.helloDubbo(name);
    }
}
