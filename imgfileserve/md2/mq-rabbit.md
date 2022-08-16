---
icon: edit
title: RabbitMQ
category: MQ
date: 2022-06-06
tag:
- RabbitMQ
---

# RabbitMQ

https://www.rabbitmq.com/

## 1、简介、安装配置

### 1.2、概念

Publisher、message、虚拟机VHost、broker、Exchange、binding、Queue、Connection(长连接)、Consumer

![image-20210929160654938](./mq-rabbit.assets/image-20210929160654938.png)

### 1.3、docker 安装

https://www.rabbitmq.com/networking.html

![image-20210929164609552](./mq-rabbit.assets/image-20210929164609552.png)

### 1.4、运行机制

![image-20210929164752957](./mq-rabbit.assets/image-20210929164752957.png)

### 1.5、Exchange类型：点对点（direct、header）、订阅（fanout、topic）

![image-20210929165249146](./mq-rabbit.assets/image-20210929165249146.png)

目前用：direct、fanout、topid

#### direct 点对点，精准匹配（完全匹配、单播模式）

![image-20210929165322771](./mq-rabbit.assets/image-20210929165322771.png)

#### fanout 订阅：广播模式

![image-20210929165442845](./mq-rabbit.assets/image-20210929165442845.png)



#### topic 订阅：订阅模式

![image-20210929165639595](./mq-rabbit.assets/image-20210929165639595.png)

### 1.6、测试：创建交换机、队列、绑定 binding

#### 创建交换机

![image-20210929170325141](./mq-rabbit.assets/image-20210929170325141.png)

-----------

#### 创建队列

![image-20210929170338550](./mq-rabbit.assets/image-20210929170338550.png)

#### 绑定binding

![image-20210929170625945](./mq-rabbit.assets/image-20210929170625945.png)

### 1.7、实例

![image-20210929171451906](./mq-rabbit.assets/image-20210929171451906.png)

#### 创建 交换机：

exchange.direct、exchange.fanout、exchange.topic

![image-20210929171917453](./mq-rabbit.assets/image-20210929171917453.png)

#### 创建 消息队列：

队列: kong、kong.emps、kong.news、kong004.news

![image-20210929171218145](./mq-rabbit.assets/image-20210929171218145.png)

#### direct 绑定 binding

exchange.direct: -> kong、kong.emps、kong.news、kong004.news

##### 发消息

![image-20210929172547787](./mq-rabbit.assets/image-20210929172547787.png)

##### 读消息

![image-20210929172613924](./mq-rabbit.assets/image-20210929172613924.png)

![image-20210929172902177](./mq-rabbit.assets/image-20210929172902177.png)

-------

Ack Mode（确认模式：）: Nack message requeue true（Nack 消息重新排队，true查看后不会清除消息队列，false反之）Automatic ack（自动确认）、Reject requeue true|false（拒绝重新排队，true查看后不会清除消息队列，false反之）、

#### fanout 绑定 binding

exchange.fanout: -> kong、kong.emps、kong.news、kong004.news

##### 发消息

![image-20210929175249961](./mq-rabbit.assets/image-20210929175249961.png)

##### 读消息

![image-20210929175407953](./mq-rabbit.assets/image-20210929175407953.png)


查看初始化交换机

![image-20220716144723052](./mq-rabbit.assets/image-20220716144723052.png)

查看初始化队列

![image-20220716145127272](./mq-rabbit.assets/image-20220716145127272.png)

--------------

模拟创建订单：http://127.0.0.1:9210/jf-system-dev/mq/sendOrderMessage

查看订单消息投递到订单延迟队列情况

![image-20220716145117155](./mq-rabbit.assets/image-20220716145117155.png)

![image-20220716144913398](./mq-rabbit.assets/image-20220716144913398.png)

1分钟后，查看处理结果

![image-20220716144848045](./mq-rabbit.assets/image-20220716144848045.png)

![image-20220716144856335](./mq-rabbit.assets/image-20220716144856335.png)





