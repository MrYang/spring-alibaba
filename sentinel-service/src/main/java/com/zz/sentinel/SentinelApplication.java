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
    @SentinelResource(value = "byResource", blockHandler = "blockHandler")
    public String resource() {
        return "资源限流";
    }

    @RequestMapping("/limit/url")
    @SentinelResource(value = "byUrl", blockHandler = "blockHandler")
    public String url() {
        return "url限流";
    }

    @RequestMapping("/fallback")
    @SentinelResource(value = "fallback", fallback = "fallbackHandler")
    public String fallback() {
        return "如果抛出异常,走熔断方法";
    }

    // 限流处理
    public String blockHandler(BlockException exception) {
        return "sentinel handle exception:" + exception.getClass().getCanonicalName();
    }

    // 熔断处理
    public String fallbackHandler() {
        return "熔断后方法";
    }
}
