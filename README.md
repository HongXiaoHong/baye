# baye

霸业

## springboot

### web

#### rest接口

#### Spring Boot非Web项目运行的方法

参考 [Spring Boot非Web项目运行的方法](https://www.jb51.net/article/169744.htm)

spring-boot-starter-web -> spring-boot-starter
```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
        <exclusion>
            <artifactId>spring-boot-starter-logging</artifactId>
            <groupId>org.springframework.boot</groupId>
        </exclusion>
    </exclusions>
</dependency>
```

只运行一次就结束 如果想要不停止 可以实现 CommandLineRunner 重写 run方法为 @Override public void run(String... args) throws Exception {
Thread.currentThread().join(); }

### 日志

#### 日志门面

常见的有：

日志门面 : 
- JCL
- slf4j

日志实现 : 
- JUL
- logback
- log4j
- log4j2

日志实现有些实现了多个日志门面

![slf4j与实现框架绑定关系](./images/slf4j-bound.png)

#### springboot 引入 log4j2

还没引入之前日志

```text

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.4.5)

2021-05-22 19:43:52.577  INFO 19252 --- [           main] c.g.c.h.s.SpringBootLearnApplication     : Starting SpringBootLearnApplication using Java 1.8.0_202 on DESKTOP-6ANCF3P with PID 19252 (D:\learn\experiment\Java\learn\spring-boot-learn\target\classes started by hong in D:\learn\experiment\Java\learn)
2021-05-22 19:43:52.579  INFO 19252 --- [           main] c.g.c.h.s.SpringBootLearnApplication     : No active profile set, falling back to default profiles: default
2021-05-22 19:43:53.140  INFO 19252 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2021-05-22 19:43:53.146  INFO 19252 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-05-22 19:43:53.146  INFO 19252 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.45]
2021-05-22 19:43:53.147  INFO 19252 --- [           main] o.a.catalina.core.AprLifecycleListener   : An older version [1.2.21] of the Apache Tomcat Native library is installed, while Tomcat recommends a minimum version of [1.2.23]
2021-05-22 19:43:53.147  INFO 19252 --- [           main] o.a.catalina.core.AprLifecycleListener   : Loaded Apache Tomcat Native library [1.2.21] using APR version [1.6.5].
2021-05-22 19:43:53.147  INFO 19252 --- [           main] o.a.catalina.core.AprLifecycleListener   : APR capabilities: IPv6 [true], sendfile [true], accept filters [false], random [true].
2021-05-22 19:43:53.147  INFO 19252 --- [           main] o.a.catalina.core.AprLifecycleListener   : APR/OpenSSL configuration: useAprConnector [false], useOpenSSL [true]
2021-05-22 19:43:53.149  INFO 19252 --- [           main] o.a.catalina.core.AprLifecycleListener   : OpenSSL successfully initialized [OpenSSL 1.1.1a  20 Nov 2018]
2021-05-22 19:43:53.194  INFO 19252 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-05-22 19:43:53.194  INFO 19252 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 582 ms
 food : Food{meet='beef', fruit='apple', vegetable='tomato', drink='milk'}
2021-05-22 19:43:53.305  INFO 19252 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2021-05-22 19:43:53.411  INFO 19252 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-05-22 19:43:53.418  INFO 19252 --- [           main] c.g.c.h.s.SpringBootLearnApplication     : Started SpringBootLearnApplication in 1.131 seconds (JVM running for 1.838)
MyApplicationRunner class will be execute when the project was started!
```

修改后

```text

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.4.5)

19:53:04.651 [main] [INFO ] cn.gd.cz.hong.springbootlearn.SpringBootLearnApplication:55 --- Starting SpringBootLearnApplication using Java 1.8.0_202 on DESKTOP-6ANCF3P with PID 19744 (D:\learn\experiment\Java\learn\spring-boot-learn\target\classes started by hong in D:\learn\experiment\Java\learn)
19:53:04.657 [main] [INFO ] cn.gd.cz.hong.springbootlearn.SpringBootLearnApplication:675 --- No active profile set, falling back to default profiles: default
19:53:05.198 [main] [INFO ] org.springframework.boot.web.embedded.tomcat.TomcatWebServer:108 --- Tomcat initialized with port(s): 8080 (http)
19:53:05.208 [main] [INFO ] org.apache.coyote.http11.Http11NioProtocol:173 --- Initializing ProtocolHandler ["http-nio-8080"]
19:53:05.208 [main] [INFO ] org.apache.catalina.core.StandardService:173 --- Starting service [Tomcat]
19:53:05.209 [main] [INFO ] org.apache.catalina.core.StandardEngine:173 --- Starting Servlet engine: [Apache Tomcat/9.0.45]
19:53:05.210 [main] [INFO ] org.apache.catalina.core.AprLifecycleListener:173 --- An older version [1.2.21] of the Apache Tomcat Native library is installed, while Tomcat recommends a minimum version of [1.2.23]
19:53:05.210 [main] [INFO ] org.apache.catalina.core.AprLifecycleListener:173 --- Loaded Apache Tomcat Native library [1.2.21] using APR version [1.6.5].
19:53:05.210 [main] [INFO ] org.apache.catalina.core.AprLifecycleListener:173 --- APR capabilities: IPv6 [true], sendfile [true], accept filters [false], random [true].
19:53:05.210 [main] [INFO ] org.apache.catalina.core.AprLifecycleListener:173 --- APR/OpenSSL configuration: useAprConnector [false], useOpenSSL [true]
19:53:05.212 [main] [INFO ] org.apache.catalina.core.AprLifecycleListener:173 --- OpenSSL successfully initialized [OpenSSL 1.1.1a  20 Nov 2018]
19:53:05.255 [main] [INFO ] org.apache.catalina.core.ContainerBase.[Tomcat].[localhost].[/]:173 --- Initializing Spring embedded WebApplicationContext
19:53:05.255 [main] [INFO ] org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext:289 --- Root WebApplicationContext: initialization completed in 541 ms
 food : Food{meet='beef', fruit='apple', vegetable='tomato', drink='milk'}
19:53:05.354 [main] [INFO ] org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor:181 --- Initializing ExecutorService 'applicationTaskExecutor'
19:53:05.448 [main] [INFO ] org.apache.coyote.http11.Http11NioProtocol:173 --- Starting ProtocolHandler ["http-nio-8080"]
19:53:05.460 [main] [INFO ] org.springframework.boot.web.embedded.tomcat.TomcatWebServer:220 --- Tomcat started on port(s): 8080 (http) with context path ''
19:53:05.467 [main] [INFO ] cn.gd.cz.hong.springbootlearn.SpringBootLearnApplication:61 --- Started SpringBootLearnApplication in 1.074 seconds (JVM running for 2.001)
MyApplicationRunner class will be execute when the project was started!
```

1. 引入 spring-boot-starter-log4j2

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

直接启动会出现下面这个报错 虽然不影响启动 但是启动爆这个错总感觉哪里不对劲

```text
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/D:/app/code/maven/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/D:/app/code/maven/repository/org/apache/logging/log4j/log4j-slf4j-impl/2.13.3/log4j-slf4j-impl-2.13.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
```

排查
![日志包引用冲突](./images/logging-conflict.png)

排除后 自动使用的原因
![日志使用sl4j原因](./images/sl4j-reson.png)

最后使用的日志框架参照
![sl4j框架转换-桥接模式](./images/sl4j-transfer-legacy.png)

##### 桥接模式

主要思想： 将继承关系转化为组合关系

### database
#### MySQL
首先你本地要安装好MySQL 这里不做赘述

1. 引入jdbc starter 还有就是MySQL驱动
```xml

```

### 配置

#### 自定义配置生成元数据

引入 spring-boot-configuration-processor jar包

可以帮助我们生成元数据 进而可以在yaml配置文件跟对应的类之间进行跳转

pom.xml 增加配置

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
</dependency>
```

spring-boot-configuration-processor

没有配置之前 idea 会出现 spring boot configuration annotation processor not configured

但是程序没有报错依旧可以正常使用

配置了 spring-boot-configuration-processor 之后错误消失

re-run spring boot configuration annotation processor to update generated metadata 重新运行Spring Boot配置注释处理器以更新生成的元数据

spring-boot-configuration-processor 这个组件是为了帮助我们自己配置生成元数据

可参考[spring-boot-configuration-processor的真实作用](https://blog.csdn.net/weixin_43328357/article/details/106993172)
