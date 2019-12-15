# Spring-Alibaba

## nacos

配置中心+注册中心

wsl 下启动 `/bin/bash bin/startup.sh -m standalone`

输入 `localhost:8848/nacos` 用户名/密码 `nacos/nacos`

### 集群安装

### 配置中心

```yaml
spring:
  application:
    name: nacos-server
  cloud:
    nacos:
      config:
        server-addr: localhost:8848
        file-extension: yaml
```

表示配置格式为yaml 格式，`@RefreshScope` 在需要配置的类加上注解

配置文件名称为 `${spring.application.name}-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}`，则该项目的配置dataid 为nacos-server-dev.yaml

### 注册中心

```yaml
spring:
  application:
    name: nacos-server
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
```

`@EnableDiscoveryClient` 添加服务发现注解

### 配合feign

## sentinel

限流熔断

启动 `java -jar sentinel-dashboard-1.7.0.jar &` 浏览器输入`http://localhost:8080` 打开控制台 用户名密码为`sentinel/sentinel`

### 限流

可以按资源及URL地址限流，在控制台中的流控规则，添加新的流控规则

使用URL地址限流时，有默认的限流处理逻辑，使用资源名称限流时，需要制定`blockHandler`限流处理逻辑

### 熔断

### 使用nacos 持久化规则

```yaml
spring:
  cloud:
    sentinel:
      datasource:
        ds1:
          nacos:
            server-addr: localhost:8848
            dataId: ${spring.application.name}-sentinel-flow
            groupId: DEFAULT_GROUP
            data-type: json
            rule-type: flow
        ds2:
          nacos:
            server-addr: localhost:8848
            dataId: ${spring.application.name}-sentinel-degrade
            groupId: DEFAULT_GROUP
            data-type: json
            rule-type: degrade
```

在nacos添加一个`sentinel-service-sentinel-degrade` dataId, 配置格式为json

```json
[
    {
        "resource": "fallback",
        "limitApp": "default",
        "grade": 1,
        "count": 1,
        "strategy": 0,
        "controlBehavior": 0,
        "clusterMode": false
    }
]
```

各个参数为:

- resource：资源名称
- limitApp：来源应用
- grade：阈值类型，0表示线程数，1表示QPS
- count：单机阈值
- strategy：流控模式，0表示直接，1表示关联，2表示链路
- controlBehavior：流控效果，0表示快速失败，1表示Warm Up，2表示排队等待
- clusterMode：是否集群

在nacos添加一个`sentinel-service-sentinel-flow` dataId, 配置格式为json

```json
[
    {
        "resource": "fallback",
        "grade": 0,
        "count": 500,
        "timeWindow": 10
    }
]
```

各个参数为:

- resource：资源名称
- grade：阈值类型，0表示平均响应时间，1表示异常比例，2表示异常数
- count：单机阈值
- timeWindow：时间窗口

## dubbo

## seata