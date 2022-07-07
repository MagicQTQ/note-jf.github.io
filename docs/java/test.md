---
title: 测试md
category: Java
date: 2020-01-01
tag:
  - Java
  - 测试
---

# 基础概念与常识
## 基础概
### Java 语言有哪些特点?
#### 有哪些特

> 哪些特哪些特哪些特哪些特哪些特
> 
> 哪些特哪些特哪些特哪些特哪些特
 

1. 简单易学；
2. 面向对象（*封装*，*继承*，**多态**）；
3. 平台无关性（ Java 虚拟机实现平台无关性）；

![container.png](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/2019-7/container.png)

```yaml
spring:
  profiles:
    # prod、 dev、test
    active: dev
  cloud:
    nacos:
      config:
        shared-configs:
          - data-id: application-${spring.profiles.active}
            group: ${spring.profiles.active}
            refresh: true
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