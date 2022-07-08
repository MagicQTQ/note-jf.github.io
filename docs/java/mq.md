---
title: MQ
category: 消息中间件
date: 2020-01-01
tag:
- mq
---

ActiveMQ,RabbitMQ,Kafka,RocketMQ

# 总览

![](./mq.assets/20170816171523564.png)

## 概念定义

**Producer** 消息生产者

​		**Producer Group** 生产者组，简单来说就是多个发送同一类消息的生产者称之为一个生产者组。在这里可以不用关心，只要知道有这么一个概念即可。

**Consumer** 消息消费者，简单来说，消费 MQ 上的消息的应用程序就是消费者，至于消息是否进行逻辑处理，还是直接存储到数据库等取决于业务需要。

​		**Consumer Group** 消费者组

**Topic** 是一种消息的逻辑分类，比如说你有订单类的消息，也有库存类的消息，那么就需要进行分类，一个是订单Topic 存放订单相关的消息，一个是库存 Topic 存储库存相关的消息。

**Message** 是消息的载体。必须指定 topic，相当于寄信的地址。可选的 tag，以便消费端可以基于 tag 进行过滤消息。也可以添加额外的键值对，例如你需要一个业务 key 来查找 broker 上的消息，方便在开发过程中诊断问题。

**Tag** 标签可以被认为是对 Topic 进一步细化。一般在相同业务模块中通过引入标签来标记不同用途的消息。

**Broker** 接收来自生产者的消息，储存以及为消费者拉取消息的请求做好准备。

**Name Server** 为 producer 和 consumer 提供路由信息。

# 一,RocketMQ

单 master 模式
 也就是只有一个 master 节点，称不上是集群，一旦这个 master 节点宕机，那么整个服务就不可用，适合个人学习使用。

多 master 模式
 多个 master 节点组成集群，单个 master 节点宕机或者重启对应用没有影响。
 优点：所有模式中性能最高
 缺点：单个 master 节点宕机期间，未被消费的消息在节点恢复之前不可用，消息的实时性就受到影响。
 **注意**：使用同步刷盘可以保证消息不丢失，同时 Topic 相对应的 queue 应该分布在集群中各个节点，而不是只在某各节点上，否则，该节点宕机会对订阅该 topic 的应用造成影响。

多 master 多 slave 异步复制模式
 在多 master 模式的基础上，每个 master 节点都有至少一个对应的 slave。master
 节点可读可写，但是 slave 只能读不能写，类似于 mysql 的主备模式。
 优点： 在 master 宕机时，消费者可以从 slave 读取消息，消息的实时性不会受影响，性能几乎和多 master 一样。
 缺点：使用异步复制的同步方式有可能会有消息丢失的问题。

多 master 多 slave 同步双写模式
 同多 master 多 slave 异步复制模式类似，区别在于 master 和 slave 之间的数据同步方式。
 优点：同步双写的同步模式能保证数据不丢失。
 缺点：发送单个消息 RT 会略长，性能相比异步复制低10%左右。
 刷盘策略：同步刷盘和异步刷盘（指的是节点自身数据是同步还是异步存储）
 同步方式：同步双写和异步复制（指的一组 master 和 slave 之间数据的同步）
 **注意**：要保证数据可靠，需采用同步刷盘和同步双写的方式，但性能会较其他方式低。



---

# 二,RabbitMQ

## 1、MQ简介、安装配置

### 1.1、场景应用：解耦，流量控制

1.大多应用中，可通过消息服务中间件来提升系统 **异步通信**、**扩展解耦能力**

2.消息服务中两个重要概念:
		**消息代理(message broker)和目的地(destination)**：当消息发送者发送消息以后，将由消息代理接管，消息代理保证消息传递到指定目的地。

3.消息队列主要有两种形式的目的地: 

**队列(queue)** : 点对点消息通信(point-to-point)

**主题(topic)** : 发布(publish)  / 订阅(subscribe) 消息通信

4.点对点式
			：消息发送者发送消息，消息代理将其放入1个队列中，消息接收者从队列中获取消息内容,消息读取后被移出队列。

​	：消息只有唯一的发送者和接受者,但并不是说只能有一个接收者

5.发布订阅式
			：发送者(发布者)发送消息到主题，多个接收者(订阅者)监听(订阅)这个主题，那么就会在消息到达时同时收到消息

6.JMS (Java Message Service) JAVA消息服务
			：基于JVM消息代理的规范。ActiveMQ、 HornetMQ是JMS实现

7.AMQP (Advanced Message Queuing Protocol)
			：高级消息队列协议，也是一个消息代理的规范，兼容JMS

​	：RabbitMQ是AMQP的实现

8.Spring 支持

```
-spring-jms提供了对JMS的支持
-spring-rabbit提供了对AMQP的支持
-需要ConnectionFactory的实现来连接消息代理
-提供JmsTemplate、RabbitTemplate来发送消息
-@JmsListener (JMS) 、@RabbitListener (AMQP) 注解在方法上监听消息代理发布的消息
-@EnableJms、 @EnableRabbit 开启支持
```

9.Spring Boot自动配置

> -JmsAutoConfiguration
>
> -RabbitAutoConfiguration

10.市面的MQ产品

> ActiveMQ、RabbitMQ、 RocketMQ、 Kafka

### 1.2、概念

Publisher、message、虚拟机VHost、broker、Exchange、binding、Queue、Connection(长连接)、Consumer

![image-20210929160654938](./mq.assets/true-image-20210929160654938.png)

### 1.3、安装

https://www.rabbitmq.com/networking.html

```bash
dkpull rabbitmq:management(默认最新)
dkpull rabbitmq:3.8.23-management
dkpull rabbitmq:3.9.7-management

docker run -d --name rabbitmq3.8.23 -p 5671:5671 -p 5672:5672 -p 4369:4369 -p 25672:25672 -p 15672:15672 rabbitmq:3.8.23-management
docker update rabbitmq3.8.23 --restart=always

docker run -d --name rabbitmq3.8.23 -p 5671:5671 -p 5672:5672 -p 4369:4369 -p 25672:25672 -p 15672:15672 rabbitmq:3.8.23-management

4369, 25672 (Erlang发现&集群端口)
5672, 5671 (AMQP端口)
15672 (web管理后台端口)
61613, 61614 (STOMP协议端口)
1883, 8883 (MQTT协议端口)
```

http://localhost:15672/#/ 密码：默认（user：guest；pass：guest）

![image-20210929164609552](./mq.assets/true-image-20210929164609552.png)

### 1.4、运行机制

![image-20210929164752957](./mq.assets/true-image-20210929164752957.png)

### 1.5、Exchange类型：点对点（direct、header）、订阅（fanout、topic）

![image-20210929165249146](./mq.assets/true-image-20210929165249146.png)

目前用：direct、fanout、topid

#### direct 点对点，精准匹配（完全匹配、单播模式）

![image-20210929165322771](./mq.assets/true-image-20210929165322771.png)

#### fanout 订阅：广播模式

![image-20210929165442845](./mq.assets/true-image-20210929165442845.png)



#### topic 订阅：订阅模式

![image-20210929165639595](./mq.assets/true-image-20210929165639595.png)

### 1.6、测试：创建交换机、队列、绑定 binding

#### 创建交换机

![image-20210929170325141](./mq.assets/true-image-20210929170325141.png)

-----------

#### 创建队列

![image-20210929170338550](./mq.assets/true-image-20210929170338550.png)

#### 绑定binding

![image-20210929170625945](./mq.assets/true-image-20210929170625945.png)

### 1.7、实例

![image-20210929171451906](./mq.assets/true-image-20210929171451906.png)

#### 创建 交换机：

exchange.direct、exchange.fanout、exchange.topic

![image-20210929171917453](./mq.assets/true-image-20210929171917453.png)

#### 创建 消息队列：

队列: kong、kong.emps、kong.news、kong004.news

![image-20210929171218145](./mq.assets/true-image-20210929171218145.png)

#### direct 绑定 binding

exchange.direct: -> kong、kong.emps、kong.news、kong004.news

##### 发消息

![image-20210929172547787](./mq.assets/true-image-20210929172547787.png)

##### 读消息

![image-20210929172613924](./mq.assets/true-image-20210929172613924.png)

![image-20210929172902177](./mq.assets/true-image-20210929172902177.png)

-------

Ack Mode（确认模式：）: Nack message requeue true（Nack 消息重新排队，true查看后不会清除消息队列，false反之）Automatic ack（自动确认）、Reject requeue true|false（拒绝重新排队，true查看后不会清除消息队列，false反之）、

#### fanout 绑定 binding

exchange.fanout: -> kong、kong.emps、kong.news、kong004.news

##### 发消息

![image-20210929175249961](./mq.assets/true-image-20210929175249961.png)

##### 读消息

![image-20210929175407953](./mq.assets/true-image-20210929175407953.png)



#### topic 绑定 binding

exchange.topic: -> kong、kong.emps、kong.news、kong004.news

![image-20210929180258342](./mq.assets/true-image-20210929180258342.png)

##### 发消息

![image-20210929181324171](./mq.assets/true-image-20210929181324171.png)

![image-20210929181347034](./mq.assets/true-image-20210929181347034.png)



##### 读消息

![image-20210929181503450](./mq.assets/true-image-20210929181503450.png)



## 2、整合java

### 2.1、配置\<a name="配置">

![image-20210929181618065](./mq.assets/true-image-20210929181618065.png)

```xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-amqp -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
    <version>2.6.7</version>
</dependency>
```

```properties
# RabbitMQ配置
spring.rabbitmq.host=192.168.101.4
spring.rabbitmq.port=5672
# 虚拟主机配置
spring.rabbitmq.virtual-host=/
# 开启发送端消息抵达Broker确认
spring.rabbitmq.publisher-confirms=true
# 开启发送端消息抵达Queue确认
spring.rabbitmq.publisher-returns=true
# 只要消息抵达Queue，就会异步发送优先回调returnfirm
spring.rabbitmq.template.mandatory=true
# 手动ack消息，不使用默认的消费端确认
spring.rabbitmq.listener.simple.acknowledge-mode=manual
```

```java
package cn.jf.system.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * RabbitMQ配置类
 *
 * @author jf
 * @version 1.0
 * @Description 描述
 * @date 2022/07/05 15:47
 */
@Slf4j
@Configuration
public class MyRabbitMQConfig {
    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Primary
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        this.rabbitTemplate = rabbitTemplate;
//        rabbitTemplate.setMessageConverter(messageConverter());
//        initRabbitTemplate();
//        return rabbitTemplate;
//    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 定制RabbitTemplate<p>
     * -@PostConstruct： MyRabbitMQConfig 对象创建完成以后，执行这个方法<p>
     * 1、服务收到消息就会回调<p>
     * ---- spring.rabbitmq.publisher-confirms: true<p>
     * ---- 设置确认回调 setConfirmCallback<p>
     * 2、消息正确抵达队列就会进行回调<p>
     * ---- spring.rabbitmq.publisher-returns: true<p>
     * ---- spring.rabbitmq.template.mandatory: true<p>
     * ---- 设置确认回调ReturnCallback<p>
     * <p>
     * 3、消费端确认(保证每个消息都被正确消费，此时才可以broker删除这个消息)<p>
     * ---- 默认是自动确认的，只要消息接收到，客户端会自动确认，服务端就会移除这个消息<p>
     * -------- 问题： 在处理信息时宕机了，会把所有的消息确认了，<p>
     * -------- 解决：需要手动确认信息，手动ack消息，不使用默认的消费端确认 spring.rabbitmq.listener.simple.acknowledge-mode=manual<p>
     * ---- 签收了货物 channel.basicAck(deliveryTag, false);拒绝签收货物channel.basicNack(deliveryTag, false, false);<p>
     */
    @PostConstruct
    public void initRabbitTemplate() {
        /*
         * 设置确认回调
         * 1、只要消息抵达Broker就ack=true
         * correlationData：当前消息的唯一关联数据(这个是消息的唯一id)
         * ack：消息是否成功收到
         * cause：失败的原因
         */
        //        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                log.info("confirm.correlationData[" + correlationData + "]==>ack:[" + ack + "]==>errorMsg:[" + cause + "]");
//            }
//        });
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("confirm.correlationData[" + correlationData + "]==>ack:[" + ack + "]==>errorMsg:[" + cause + "]");
        });
        /*
         * 只要消息没有投递给指定的队列，就触发这个失败回调
         * message：投递失败的消息详细信息
         * replyCode：回复的状态码
         * replyText：回复的文本内容
         * exchange：当时这个消息发给哪个交换机
         * routingKey：当时这个消息用哪个路由键
         */
        rabbitTemplate.setReturnsCallback((returned) -> {
            log.error("==>errorMsg[" + returned.getMessage() + "] ==>code[" + returned.getReplyCode() + "]" +
                    "==>text[" + returned.getReplyText() + "] ==>exchange[" + returned.getExchange() + "] ==>routingKey[" + returned.getRoutingKey() + "].\r\n");
        });
//        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
//            @Override
//            public void returnedMessage(ReturnedMessage returned) {
//                log.error("==>errorMsg[" + returned.getMessage() + "] ==>code[" + returned.getReplyCode() + "]" +
//                        "==>text[" + returned.getReplyText() + "] ==>exchange[" + returned.getExchange() + "] ==>routingKey[" + returned.getRoutingKey() + "].\r\n");
//            }
//        });
    }
}
```

@EnableRabbit

### 2.2 amqpAdmin 管理组件


private AmqpAdmin amqpAdmin;

#### 创建交换机、创建队列、绑定binding

```java
//创建交换机：
Exchange directExchange = new DirectExchange("hello-java-exchange", true, false);
amqpAdmin.declareExchange(directExchange);
//创建队列：
Queue queue = new Queue("hello-java-queue", true, false, false);
amqpAdmin.declareQueue(queue);
//绑定binding：
Binding binding = new Binding("hello-java-queue",
				Binding.DestinationType.QUEUE,
				"hello-java-exchange",
				"hello.java",
				null);
amqpAdmin.declareBinding(binding);
```

### 2.3 RabbitTemplate 消息

private RabbitTemplate rabbitTemplate;

#### 发消息

```java
public void sendMessageTest(){
   OrderReturnReasonEntity reasonEntity = new OrderReturnReasonEntity();
   reasonEntity.setId(1L);
   reasonEntity.setCreateTime(new Date());
   reasonEntity.setName("reason");
   reasonEntity.setStatus(1);
   reasonEntity.setSort(2);
   String msg = "Hello World";
    
   //1、发送消息,如果发送的消息是个对象，会使用序列化机制，将对象写出去，对象必须实现Serializable接口
   //2、发送的对象类型的消息，可以是一个json
   rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java",
         reasonEntity, new CorrelationData(UUID.randomUUID().toString()));
   log.info("消息发送完成:{}", reasonEntity);
}
```

#### 监听队列：

#### 注意：同一个消息，只能有一个客户端收到；只有一个消息完全处理完，方法运行结束，我们就可以接收到下一个消息

##### @RabbitListener 作用在类+方法上 (监听哪些队列即可）

```java
/**
	 * queues：声明需要监听的队列<p>
	 * Channel：当前传输数据的通道<p>
	 * message：原生消息信息（包含信息头+消息体）<p>
	 * content：信息具体内容<p>
	 * <p>
	 * <p>
	 * Queue：<p>
	 * 可以很多人都来监听。只要收到消息，队列删除消息，而且只能有一个收到此消息<p>
	 * 场景:<p>
	 * 1)、订单服务启动多个;同一个消息，只能有一-个客户端收到<p>
	 * 2)、只有一个消息完全处理完，方法运行结束，我们就可以接收到下一个消息<p>
	 * -@RabbitListener 类+方法上 (监听哪些队列即可）<p>
	 * -@RabbitHandler: 标在方法上(重载区分不同的消息)<p>
	 */
	@RabbitListener(queues = {"hello-java-queue"})
	public void revieveMessage(Message message, OrderReturnReasonEntity content, Channel channel) throws InterruptedException{
		System.out.println("原生消息信息--"+message+"\r\n===内容："+content);
		System.out.println("----OrderReturnReasonEntity 消息处理完成----");
	}
```

##### @RabbitHandler 仅作用在方法（重载区分不同的消息）

http://localhost:8200/order/order/sendMQ

```java
@RequestMapping("sendMQ")
	public String sendMessage(@RequestParam(value = "num", defaultValue = "10") Integer num){
		for(int i = 0; i < num; i++){
			if(i%2 == 0){
				OrderReturnReasonEntity reasonEntity = new OrderReturnReasonEntity();
				reasonEntity.setName("--OrderReturnReasonEntity--"+i);
				rabbitTemplate.convertAndSend("hello-java-exchange", "hello-java-queue.java",
						reasonEntity, new CorrelationData(UUID.randomUUID().toString()));
			}else{
				OrderEntity orderEntity = new OrderEntity();
				orderEntity.setOrderSn(UUID.randomUUID().toString());
				orderEntity.setReceiverName("--OrderEntity--"+i);
				rabbitTemplate.convertAndSend("hello-java-exchange", "hello-java-queue.java",
						orderEntity, new CorrelationData(UUID.randomUUID().toString()));
			}
		}
		return "消息发送完成";
	}

@RabbitHandler
public void revieveMessage02(OrderReturnReasonEntity content){
   System.out.println("原生消息信息--"+content);
   System.err.println("----OrderReturnReasonEntity 消息处理完成----");
}

@RabbitHandler
public void revieveMessage03(OrderEntity content){
   System.out.println("原生消息信息--"+content);
   System.err.println("----OrderEntity 消息处理完成----");
}
```



## 3、投递消息-安全到达

<img src="./mq.assets/true-image-20211003221447339.png" alt="image-20211003221447339" style="zoom: 80%;" />

https://www.rabbitmq.com/reliability.html

![image-20211003222246765](./mq.assets/true-image-20211003222246765.png)![image-20211003234548905](./mq.assets/true-image-20211003234548905.png)



[请看2.1 配置]()

### 3.1、发送端确认

[请看2.1 配置config]()：setConfirmCallback、setReturnCallback

### 3.2、消费端确认

```
3、消费端确认(保证每个消息都被正确消费，此时才可以broker删除这个消息)<p>
---- 默认是自动确认的，只要消息接收到，客户端会自动确认，服务端就会移除这个消息
-------- 问题： 在处理信息时宕机了，会把所有的消息确认了，
-------- 解决：需要手动确认信息，手动ack消息，不使用默认的消费端确认spring.rabbitmq.listener.simple.acknowledge-mode=manual
---- 签收了货物 channel.basicAck(deliveryTag, false);拒绝签收货物channel.basicNack(deliveryTag, false, false);
```

<img src="./mq.assets/true-image-20211003233121163.png" alt="image-20211003233121163" style="zoom:90%;" />

```java
@RabbitHandler
public void revieveMessage02(Message message, OrderReturnReasonEntity content, Channel channel){
   System.out.println("原生消息信息--"+content);
   long deliveryTag = message.getMessageProperties().getDeliveryTag();
   //签收货物,非批量模式
   try{
      if(deliveryTag%2 == 0){
         //收货
         channel.basicAck(deliveryTag, false);
         System.out.println("签收了货物... "+deliveryTag);
      }else{
         //退货requeue=false 丢弃requeue=true 发回服务器，服务器重新入队。
         //long deliveryTag, boolean multiple, boolean requeue
         channel.basicNack(deliveryTag, false, false);
         //Long deliveryTag, boolean requeue
         //channel.basicReject();
         System.out.println("没有签收了货物..."+deliveryTag);
      }
   }catch(Exception e){
      //网络中断
   }

   System.err.println("----OrderReturnReasonEntity 消息处理完成----");
}
```

## 4、MQ延迟队列<a name="MQ延迟队列"></a>

### 4.1、使用场景

场景:
	比如未付款订单，超过一定时间后，系统自动取消订单并释放占有物品。

常用解决方案:
	spring的schedule *定时任务* 轮询数据库
		缺点：消耗系统内存、增加了数据库的压力、存在较大的时间误差
解决：rabbitmq的消息 TTL 和死信 Exchange 结合

![image-20211011180618706](./mq.assets/true-image-20211011180618706.png)

### 4.2、时效性问题

![image-20211011180634413](./mq.assets/true-image-20211011180634413.png)

### 4.3、TTL（消息的存活时间）和死信 Exchange

```bash
#·消息的TTL就是消息的存活时间。
#·RabbitMQ可以对队列和消息分别设置TTL：
    #-对队列设置就是队列没有消费者连着的保留时间，也可以对每一个单独的消息做单独的设置。超过了这个时间，我们认为这个消息就死了，称之为死信。
    #- 如果队列设置了，消息也设置了,那么会取小的。所以一个消息如果被路由到不同的队列中，这个消息死亡的时间有可能不一-样(不同的队列设置)。这里单讲单个消息的TTL,因为它才是实现延迟任务的关键。可以通过设置消息的expiration字段或者x-message-ttI属性来设置时间，两者是一样的效果。

```

### 4.4、延迟队列实现

#### - - 设置 队列 过期时间

设计建议规范：（基于事件模型的交换机设计）
	1、交换机命名：业务+ exchange; 交换机为Topic
	2、路由键：事件+需要感知的业务(可以不写)
	3、队列命名：事件+想要监听服务名+ queue
	4、绑定关系：事件+感知的业务(#)

![image-20211011185404410](./mq.assets/true-image-20211011185404410.png)

1

![image-20211011185812462](./mq.assets/true-image-20211011185812462.png)

![image-20211011213947643](./mq.assets/true-image-20211011213947643.png)

<img src="./mq.assets/true-image-20211108204644677.png" alt="image-20211108204644677" style="zoom:100%;" />

```java
/**
 * 死信队列
 * <p>
 * 对队列设置过期，而不是对消息设置过期
 *
 * @return
 */
@Bean
public Queue orderDelayQueue(){
  HashMap<String, Object> arguments = new HashMap<>();
  arguments.put("x-dead-letter-exchange", "order-event-exchange");
  arguments.put("x-dead-letter-routing-key", "order.release.order");
  // 消息过期时间 1分钟
  arguments.put("x-message-ttl", 60000);
  //消息过期后丢到order.delay.queue队列，而不是删除
  return new Queue("order.delay.queue", true, false, false, arguments);
}
```



#### - - 设置 消息 过期时间



![image-20211011185527444](./mq.assets/true-image-20211011185527444.png)

#### - - 创建业务交换机&队列







------------

# 三,ActiveMQ









-------------

# 四,Kafka

