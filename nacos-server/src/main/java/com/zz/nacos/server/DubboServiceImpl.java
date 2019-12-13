package com.zz.nacos.server;

import com.zz.dubbo.service.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class DubboServiceImpl implements DubboService {

    @Value("${server.port}")
    private String port;

    @Override
    public String helloDubbo(String name) {
        return "Hello dubbo:" + name + ", port:" + port;
    }
}
