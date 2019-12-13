package com.zz.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }

    @RequestMapping("/limit/resource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public String resource() {
        return "资源限流";
    }

    @RequestMapping("/limit/url")
    @SentinelResource(value = "byUrl")
    public String url() {
        return "url限流";
    }

    public String handleException(BlockException exception) {
        return "sentinel handle exception:" + exception.getClass().getCanonicalName();
    }
}
