---
title: Dubbo + Spring Boot 实现微服务治理
description: 通过Dubbo 结合 Spring Boot 探索微服务的治理
---
Dubbo 是国内阿里系的一个开源框架，提供基于RPC的微服务的治理框架。本文主要探索dubbo 和 spring boot 的结合, 采用https://github.com/dubbo/dubbo-spring-boot-project 来实现dubbo和spring boot的结合。（新的Spring Boot Start貌似还在研发中。）
主要探索和实现如下几项：
 1. 服务注册与发现
 2. 服务提供方和消费方连通
 3. 服务管理与监控 （dubbo-admin  dubbo-monitor）
 4. 负载均衡
 4. 跨服务日志追踪 （traceId的实现）
 5. RPC 调用的异常处理

## 示例代码
https://github.com/choelea/dubboot-example

## 架构图
![](http://tech.jiu-shu.com/Micro-Services/duboo.png)
分两个服务提供方：product和promotion，一个消费方web demo。web对外提供restful的服务，内部采用rpc协议。
> 这里product服务的模型利用了[MongoDB vs Mysql 测试](http://tech.jiu-shu.com/Database-Technologies/Mongodb-vs-Mysql-basic) 的模型,代码和数据库。
## 项目结构介绍
![Dubbo-Spring-Boot-Maven](http://tech.jiu-shu.com/Micro-Services/duboo-projects.png)
## 服务注册
注册采用推荐的zookeeper，具体安装不在这里赘述。
服务注册和监控这里没有详细介绍，需要根据实际情况修改以下配置：

```
spring.dubbo.registry.address=zookeeper://192.168.1.99:2181
spring.dubbo.monitor.address=192.168.1.99:7070
```

## 服务管理和监控
checkout dubbo工程的最新的release 代码，参考开发手册http://dubbo.io/books/dubbo-dev-book/ 构建。构建完成后，分别在dubbo-simple\dubbo-monitor-simple 和 dubbo-admin工程里找到相应的dubbo-monitor-simple-2.5.8-assembly.tar.gz和dubbo-admin-2.5.8.war。 参考http://dubbo.io/books/dubbo-admin-book/ 进行部署。
## 跨服务日志追踪
### 扩展调用拦截
通过自定义调用拦截，将traceId透传给服务提供方。
> 这里只探索，所以只用了一个filter来供服务方和消费方用，实际中可能分开个filter更合适。

参考dubbo开发手册的SPI的调用拦截扩展，自定义filter来来完成traceid的透传。（参考：http://blog.csdn.net/coolsky600/article/details/63684046）
扩展需要注意一下：

 - META-INF/dubbo/com.alibaba.dubbo.rpc.Filter中添加：`xxx=com.xxx.XxxFilter`
 - service的申明出设置filter `@Service(version = "1.0.0", filter="traceIdFilter")`

具体实现请参考dubboot-trace-log.
### 增强日志功能让日志可以打印traceId
参考文章：https://moelholm.com/2016/08/16/spring-boot-enhance-your-logging/
traceId的发起位置是从web-demo 接受到请求开始。 具体参考：com.dubboot.webdemo.web.filter.TraceLoggingFilter。
### 测试验证
依次启动product，promotion和web-demo服务，然后访问http://localhost:8080/product/pp-0 观察后面的日志情况。（zookeeper需要提前启动好保持运行状态）
### 负载均衡测试
修改端口，启动多个promotion服务，查看日志来验证随机算法的负载均衡。
> 生产环境，在同一个容器中运行同一个服务的多个实例并不是最佳实践。为了充分利用系统的资源，可以选择启动多种服务的实例，不同服务的实例分布在不同的容器/机器上。 同一个服务的端口不变，IP地址或者hostname会不同。

