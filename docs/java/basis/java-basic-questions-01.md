---
title: Java基础常见知识&面试题总结(上)
category: Java
date: 2020-01-01
tag:
  - Java基础
---

## 基础概念与常识

### Java 语言有哪些特点?

1. 简单易学；
2. 面向对象（*封装*，*继承*，**多态**）；
3. 平台无关性（ Java 虚拟机实现平台无关性）；
4. 支持多线程（ C++ 语言没有内置的多线程机制，因此必须调用操作系统的多线程功能来进行多线程程序设计，而 Java 语言却提供了多线程支持）；
5. 可靠性；
6. 安全性；
7. 支持网络编程并且很方便（ Java 语言诞生本身就是为简化网络编程设计的，因此 Java 语言不仅支持网络编程而且很方便）；
8. 编译与解释并存；

![container.png](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/2019-7/container.png)
![](https://img-blog.csdnimg.cn/20210412220015698.png)

```yaml
server:
  port: 9210
spring:
  main:
    allow-circular-references: true
  profiles:
    # prod、 dev、test
    active: dev
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
        group: ${spring.profiles.active}
      config:
        server-addr: 127.0.0.1:8848
        namespace: 76155597-f540-4a28-99b5-3980c50f69db
        file-extension: yml
        # 共享配置
        shared-configs:
          - data-id: application-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}
            group: ${spring.profiles.active}
            refresh: true
          - data-id: datasource-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}
            group: ${spring.profiles.active}
            refresh: true
          - data-id: redis-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}
            group: ${spring.profiles.active}
            refresh: true
    sentinel:
      transport:
        #配置 Sentinel dashboard 地址
        dashboard: localhost:8900
        #默认8719端口，假如被占用会自动从8719开始依次+1扫描,直至找到未被占用的端口
        port: 8719
      # sentinel持久化
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
    # 虚拟主机配置
    virtual-host: /
    # 开启发送端消息抵达Broker确认类型【simple、correlated、none】
    publisher-confirm-type: simple
    # 开启发送端消息抵达Queue确认
    publisher-returns: true
    # 只要消息抵达Queue，就会异步发送优先回调 returnfirm
    template:
      mandatory: true
    # 手动ack消息，不使用默认的消费端确认
    listener:
      simple:
        acknowledge-mode: manual

```

```java
@FeignClient("yumall-search")
public interface SearchFeignService {
    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}


/**
 * @author jf
 * @version 1.0
 * @Description 描述
 * @date 2022/07/05 15:46
 */
@Configuration
public class FeignConfig {

    @Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                //1、使用RequestContextHolder拿到刚进来的请求数据
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (requestAttributes != null) {
                    //老请求
                    HttpServletRequest request = requestAttributes.getRequest();
                    if (request != null) {
                        //2、同步请求头的数据（主要是cookie）
                        //把老请求的cookie值放到新请求上来，进行一个同步
                        String cookie = request.getHeader("Cookie");
                        template.header("Cookie", cookie);
                    }
                }
            }
        };
    }
}

```