# springcloud

> 什么是微服务

微服务架构是一种架构模式， 或者说是一种架构风格，它提倡将单一 的应用程序划分成一组小的服务，每个服务运行在其独立的自己的进程内，服务之间互相协调，互相配置，为用户提供最终价值。服务之间采用轻量级的通信机制互相沟通，每个服务都围绕着具体的业务进行构建，并且能够被独立的部署到生产环境中，另外,应尽量避免统一的，集中式的服务管理机制，对具体的一个服务而言，应根据业务上下文,选择合适的语言，工具对其进行构建，可以有一个非常轻量级的集中式管理来协调这些服务，可以使用不同的语言来编写服务，也可以使用不同的数据存储; 

> 微服务的优点和缺点

1. 优点
   - 每个服务足够内聚，足够小，代码容易理解，这样能聚焦另一个指定的业务功能或业务需求
   - 开发简单，开发效率提高，一个服务可能就是专一的只干一件事;
   - 微服务能够被小团队单独开发，这个小团队是2~5人的开发人员组成;
   - 微服务是松耦合的，是有功能意义的服务,无论是在开发阶段或部署阶段都是独立的。
   - 微服务能使用不同的语言开发。
   - 易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具,如jenkins, Hudson,bamboo
   - 微服务易于被-一个开发人员理解，修改和维护,这样小团队能够更关注自己的工作成果。无需通过合作才能体现价值。
   - 微服务允许你利用融合最新技术。
   - **微服务只是业务逻辑的代码，不会和HTML，CSS 或其他界面混合**
   - **每个微服务都有自己的存储能力，可以有自己的数据库，也可以有统一数据库**
2. 缺点
   - 开发人员要处理分布式系统的复杂性
   - 多服务运维难度，随着服务的增加，运维的压力也在增大
   - 系统部署依赖
   - 服务间通信成本
   - 数据一致性
   - 系统集成测试
   - 性能监控....

>微服务技术栈有哪些

| 微服务条目                               | 落地技术                                                     |
| ---------------------------------------- | ------------------------------------------------------------ |
| 服务开发                                 | SpringBoot，Spring，SpringMVC                                |
| 服务配置与管理                           | Netflix公司的Archaius，阿里的DIamond等                       |
| 服务注册与发现                           | Eurka，Consul，Zookeeper等                                   |
| 服务调用                                 | Rest（微服务通信），RPC（Dubbo），gRPC                       |
| 服务熔断器                               | Hystrix，Envoy等                                             |
| 负载均衡                                 | Nginx，Ribbon等                                              |
| 服务接口调用（客户端调用服务的简化工具） | Feign, openFeign等                                           |
| 消息队列                                 | kafka，RabbitMQ，ActiveMQ等                                  |
| 服务配置中心管理                         | SpringCloudConfig，Chef等                                    |
| 服务路由（API网关）                      | Zuul等                                                       |
| 服务监控                                 | Zabbix，Nagios，Metrics，Spectator等                         |
| 全链路追踪                               | Zipkin，Brave，Dapper等                                      |
| 服务部署                                 | Docker，OpenStack，Kubernetes等                              |
| 数据流操作开发包                         | SpringCloud Stream（封装与Redis，Rabbit，kafka等发送接收消息） |
| 事件消息总线                             | Spring Cloud Bus                                             |
| ...                                      | ...                                                          |

## 一，Spring Cloud

#### 1，简介

Spring Cloud,基于Spring Boot提供了一套微服务解决方案，包括服务注册与发现，配置中心，全链路监控,服务网关，负载均衡,熔断器等组件，除了基于NetFlix的开源组件做高度抽象封装之外，还有一些选型中立的开源组件。

Spring Cloud利用Spring Boot的开发便利性，巧妙地简化了分布式系统基础设施的开发，Spring Cloud为开发人员提供了快速构建分布式系统的一些工具，**包括配置管理，服务发现，断路器，路由，微代理，事件总线，全局锁，决策竞选，分布式会话等等,** 他们都可以用Spring Boot的开发风格做到一键启动和部署。

Spring Boot并没有重复造轮子,它只是将目前各家公司开发的比较成熟。经得起实际考研的服务框架组合起来,通过Spring Boot风格进行再封装，屏蔽掉了复杂的配置和实现原理，最**终给开发者留出了一套简单易懂，易部署和易维护的分布式系统开发工具包**

Spring Cloud是分布式微服务架构下的一站式解决方案,是各个微服务架构落地技术的集合体，俗称微服务全家桶。

#### 2，Spring Cloud和Spring boot的关系

- Spring Boot专注于快速方便的开发单个个体微服务。
- Spring Cloud是关注全局的微服务协调整理治理框架，它将Spring Boot开发的一个个单体微服务整合并管理起来，为各个微服务之间提供:配置管理，服务发现，断路器，路由，微代理，事件总线，全局锁，决策竞选，分布式会话等等集成服务。
- Spring Boot可以离开Spring Cloud独立使用，开发项目,但是Spring Cloud离不开Spring Boot,属于依赖关系
- **Spring Boot专注于快速、方便的开发单个个体微服务，Spring Cloud关注全局的服务治理框架**

#### 3，Dubbo和Spring Cloud对比

|              | Dubbo         | Spring                       |
| ------------ | ------------- | ---------------------------- |
| 服务注册中心 | Zookeeper     | Spring cloud Netfilx Eureka  |
| 服务调用方式 | RPC           | REST API                     |
| 服务监控     | Dubbo-monitor | Spring Boot Admin            |
| 断路器       | 不完善        | Spring Cloud Netfilx Hystrix |
| 服务网关     | 无            | Spring Cloud Netfilx Zuul    |
| 分布式配置   | 无            | Spring Cloud Config          |
| 服务跟踪     | 无            | Spring Cloud Sleuth          |
| 消息总线     | 无            | Spring Cloud Bus             |
| 数据流       | 无            | Spring Cloud Stream          |
| 批量任务     | 无            | Spring Cloud Task            |

**最大区别: SpringCloud抛弃 了Dubbo的RPC通信，采用的是基于HTTP的REST方式。**
严格来说，这两种方式各有优劣。虽然从一定程度上来说，后者牺牲了服务调用的性能，但也避免了上面提到的原生RPC带来的问题。而且REST相比RPC更为灵活，服务提供方和调用方的依赖只依靠一纸契约, 不存在代码级别的
强依赖，这在强调快速演化的微服务环境下，显得更加合适。
**品牌机与组装机的区别**
很明显，Spring Cloud的功能比DUBBO更加强大，涵盖面更广。而且作为Spring的拳头项目，它也能够与Spring Framework. Spring Boot. Spring Data. Spring Batch等其他Spring项目完美融合,这些对于微服务而言是至关重要的。使用Dubbo构建的微服务架构就像组装电脑，各环节我们的选择自由度很高，但是最终结果很有可能因为一条内存质量不行就点不亮了,总是让人不怎么放心，但是如果你是一名高手， 那这些都不是问题;而SpringCloud就像品牌机,在Spring Source的整合下,做了大量的兼容性测试，保证了机器拥有更高的稳定性,但是如果要在使用非原装组件外的东西，就需要对其基础有足够的了解。
**设计模式和微服务拆分思想**

#### 4，Spring Cloud能干什么

- Distributed/versioned configuration (分布式版本控制配置）
- Service registration and discovery (服务注册与发现)
- Routing (路由)
- Service-to-service calls (服务到服务的调用)
- Load balancing (负载均衡配置)
- Circuit Breakers (断路器)
- Distributed messaging (分布式消息管理)
- ...

## 二，Eureka

>Eureka服务注册与发现

### 1，什么是Eureka

- Netflix在设计Eureka时，遵循的就是AP原则
- Eureka是Netflix的一个子模块，也是核心模块之一。 Eureka是一个基于REST的服务，用于定位服务，以实现
  云端中间层服务发现和故障转移,服务注册与发现对于微服务来说是非常重要的，有了服务发现与注册，只需要使用服务的标识符，就可以访问到服务，而不需要修改服务调用的配置文件了,功能类似于Dubbo的注册中心，比如Zookeeper;

### 2，原理讲解

- Eureka的基本架构
  - Spring Cloud封装了NetFlix公司开发的Eureka模块来实现服务注册和发现(对比Zookeeper)
  - Eureka采用了C-S的架构设计，EurekaServer 作为服务注册功能的服务器，他是服务注册中心而系统中的其他微服务。使用Eureka的客户端连接到EurekaServer并维持心跳连接。 这样系统的维护人员就可以通过EurekaServer来监控系统中各个微服务是否正常运行，SpringCloud的- 一些其他模块(此如Zuul)就可以通过EurekaServer来发现系统中的其他微服务，并执行相关的逻辑;
  - Eureka包含两个组件: Eureka Server和Eureka Client。
  - Eureka Server提供服务注册服务，各个节点启动后，会在EurekaServer中进行注册， 这样Eureka Server中的服务注册表中将会村粗所有可用服务节点的信息，服务节点的信息可以在界面中直观的看到。
  - EurekaClient是一个Java客户端, 用于简化EurekaServer的交互,客户端同时也具备一个内置的,使用轮询负载算法的负载均衡器。在应用启动后，将会向EurekaServer发送心跳(默认周期为30秒) .如果EurekaServer在多个心跳周期内没有接收到某个节点的心跳，EurekaServer将会从服务注册表中把这个服务节点移除掉(默认周期为90秒)
- 三大角色
  - Eureka Server:提供服务的注册于发现。
  - Service Provider:将自身服务注册到Eureka中,从而使消费方能够找到。
  - Service Consumer:服务消费方从Eureka中获取注册服务列表，从而找到消费服务。

#### 自我保护机制

> 某时刻某-一个微服务不可以用了 ，eureka不会立刻清理，依旧会对该微服务的信息进行保存!

- 默认情况下，如果EurekaServer在一定时间内没有接收到某 个微服务实例的心跳，EurekaServer将会注销该
  实例(默认90秒) .但是当网络分区故障发生时，微服务与Eureka之间无法正常通行，以上行为可能变得非常危险了--因为微服务本身其实是健康的，此时本不应该注销这个服务。Eureka通过 自我保护机制来解决这个问题--当EurekaServer节点在短时间内丢失过多客户端时(可能发生了网络分区故障)，那么这个节点就会进入自我保护模式。一旦进入该模式，EurekaServer就会保护服务注册表中的信息,不再删除服务注册表中的数据(也就是不会注销任何微服务)。 当网络故障恢复后，该EurekaServer节 点会自动退出自我保护模式。
- 在自我保护模式中，EurekaServer会保护服务注册表中的信息， 不再注销任何服务实例。当它收到的心跳数重新恢复到阅值以上时，该EurekaServer 节点就会自动退出自我保护模式。它的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。一句话:好死不如赖活着
- 综上,自我保护模式是一-种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务(健康的微服务和不健康的微服务都会保留) , 也不盲目注销任何健康的微服务。使用自我保护模式，可以让Eureka集群更加的健壮和稳定在SpringCloud中,可以使用eureka.server.enable-selfpreservation=fa1se 禁用自我保护模式[不推荐关闭自我保护机制]

## 三，Ribbon

#### ribbon是什么？

- Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具
- 简单的说Ribbon是 Netflix发布的开源项目，主要功能是提供客户端的软件出在均衡算法，将Netflix的中间层服务连接在一起。Ribbon的客户端组件提供一系列完整的配置项如:连接超时、重试等等。简单的说，就是在配置文件中列出LoadBalancer (简称LB:负载均衡)后面所有的机器，Ribbon会 自动的帮助你基于某种规则(如简单轮询，随机连接等等)去连接这些机器。我们也很容易使用Ribbon实现自定义的负载均衡算法!

#### Ribbon能干什么？

- LB,即负载均衡(Load Balance)，在微服务或分布式集群中经常用的一种应用。
- 负载均衡简单的说就是将用户的请求平摊的分配到多个服务上,从而达到系统的HA (高可用)。
- 常见的负载均衡软件有Nginx, Lvs 等等
- dubbo、SpringCloud中均给我们提供 了负载均衡，**SpringCloud的负载均衡算法可以自定义**
- 负载均衡的简单分类
  - 集中式LB
    - 即在服务的消费方和提供方之间使用独立的LB设施，如Nginx, 由该设施负责把访问请求通过某种策略转发至服务的提供方!
  - 进程式LB
    - 将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选出一个合适的服务器。
    - Ribbon就属于进程内LB，它只是一个类库,集成于消费方进程，消费方通过它来获取到服务提供方的地址

## 四，Hystrix

