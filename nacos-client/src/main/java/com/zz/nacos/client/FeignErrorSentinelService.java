package com.zz.nacos.client;


public class FeignErrorSentinelService implements FeignSentinelService {

    @Override
    public String limitUrl() {
        return "limit Url 熔断";
    }
}
