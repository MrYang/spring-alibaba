package com.zz.nacos.client;

import org.springframework.stereotype.Component;

@Component
public class FeignErrorHelloService implements FeignNacosServerService {

    @Override
    public String hello(String name) {
        return "hello:" + name + ", error!!";
    }
}
