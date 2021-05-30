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

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
</dependency>
```

2. application.yml 增加配置

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    password: 123
    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
```

#### druid

1. 添加druid-spring-boot-starter

```xml

<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.10</version>
</dependency>
```

2. application.yaml 添加配置

```yaml
spring:
  # 数据源配置
  # MYSQL 5 驱动：com.mysql.jdbc.Driver，MYSQL 6+ 驱动：com.mysql.cj.jdbc.Driver
  datasource:
    type: "com.alibaba.druid.pool.DruidDataSource"
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: 123
    # 连接池配置
    druid:
      # 初始化大小，最小，最大
      initial-size: 5
      min-idle: 5
      max-active: 20
      # 配置获取连接等待超时的时间
      max-wait: 60000
      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位毫秒
      time-between-eviction-runs-millis: 60000
      # 配置一个连接在池中最小生存时间
      min-evictable-idle-time-millis: 300000
      validation-query: SELECT 1 FROM sys_user
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      # 打开 PSCache，并且指定每个连接上 PSCache 的大小
      pool-prepared-statements: true
      max-pool-prepared-statement-per-connection-size: 20
      # 配置监控统计拦截的 Filter，去掉后监控界面 SQL 无法统计，wall 用于防火墙
      filters: stat,wall,log4j2
      # 通过 connection-properties 属性打开 mergeSql 功能；慢 SQL 记录
      connection-properties: druid.stat.mergeSql\=true;druid.stat.slowSqlMillis\=5000
      # 配置 DruidStatFilter
      web-stat-filter:
        enabled: true
        url-pattern: /*
        exclusions: .js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*
      # 配置 DruidStatViewServlet
      stat-view-servlet:
        url-pattern: /druid/*
        # IP 白名单，没有配置或者为空，则允许所有访问
        allow: 127.0.0.1
        # IP 黑名单，若白名单也存在，则优先使用
        deny: 192.168.31.253
        # 禁用 HTML 中 Reset All 按钮
        reset-enable: false
        # 登录用户名/密码
        login-username: root
        login-password: 123
```

这里出现一个错误 SPRING BOOT :NO CONVERTER FOUND CAPABLE OF CONVERTING FROM TYPE [JAVA.LANG.STRING] TO TYPE
可以参考 [SPRING BOOT :NO CONVERTER FOUND CAPABLE OF CONVERTING FROM TYPE -JAVA.LANG.STRING- TO TYPE](https://www.freesion.com/article/1986388352/)

# 配置监控统计拦截的 Filter，去掉后监控界面 SQL 无法统计，wall 用于防火墙

      filters: stat,wall,log4j2

这里的配置 log4j2 估计跟换了日志框架 log4j2 有关 这里需要注意一下

druid 配置参考
[SpringBoot 三种方式配置 Druid](https://www.cnblogs.com/yjq520/p/10779356.html)

#### mybatis

1. 导入 mybatis-spring-boot-starter jar 包

```xml

<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.4</version>
</dependency>
```

2. 配置 实体类跟配置文件的位置

```yaml
mybatis:
  # 实体类
  type-aliases-package: cn.gd.cz.hong.springbootlearn.entity
  # 配置文件
  mapper-locations: mapper/*/*Mapper.xml

```

3. 启动类配置扫描mapper

```java
@MapperScan("cn.gd.cz.hong.springbootlearn.dao")
```

4. 编写接口

```java

@Mapper
public interface UserDao {

    User selectById(String id);
}
```

5. 编写sql

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="cn.gd.cz.hong.springbootlearn.dao.UserDao">
    <resultMap id="BaseResultMap" type="cn.gd.cz.hong.springbootlearn.entity.User">
        <id column="id" property="id" jdbcType="VARCHAR"/>
        <result column="name" property="name" jdbcType="VARCHAR"/>
        <result column="password" property="password" jdbcType="VARCHAR"/>
        <result column="birthday" property="birthday" jdbcType="VARCHAR"/>
    </resultMap>
    <sql id="Base_Column_List">
        id, name, password, birthday
    </sql>
    <select id="selectById" resultMap="BaseResultMap" parameterType="java.lang.String">
        select
        <include refid="Base_Column_List"/>
        from user
        where id = #{id}
    </select>
</mapper>
```

参考[SpringBoot 整合 Mybatis 和 Mysql （详细版）](https://www.cnblogs.com/wangzh1guo/p/9996526.html)

6. 自测

```java
private static final Logger LOGGER=
        LoggerFactory.getLogger(UserController.class);

@Autowired
    UserDao userDao;

@PostConstruct
public void init(){
        User user=userDao.selectById("1");
        LOGGER.info("user : {}",user);
        }
```

#### mybatis-plus

学它 [MyBatis-Plus 用起来真的很舒服](https://www.cnblogs.com/l-y-h/p/12859477.html)

##### 初识

添加pom.xml

```xml

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.2</version>
</dependency>
```

mapper 只要集成 BaseMapper 就可以使用默认的方法 泛型的类 = 实体类 = 用于mybatis-plus时 类名小写即是表名 可参见 UserMapper

##### 自动生成代码

添加 pom.xml

```xml
<!-- 自动生成代码 -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.4.1</version>
</dependency>
        <!-- 添加 模板引擎 依赖 -->
<dependency>
<groupId>org.apache.velocity</groupId>
<artifactId>velocity-engine-core</artifactId>
<version>2.3</version>
</dependency>
        <!-- 这里限制了版本号 是因为高版本的没有了类 自动生成类会报错-->
<dependency>
<groupId>org.apache.commons</groupId>
<artifactId>commons-lang3</artifactId>
<version>3.7</version>
</dependency>
        <!-- 自动生成代码 -->
```

具体的生成类参见
> cn/gd/cz/hong/springbootlearn/auto/genarate/AutoGenerationTest.java

自动生成 mapper mapper.xml service controller entity

期间出现一次错误
> Unsatisfied dependency expressed through field 'baseMapper';

原因是mapper 没有加上@Mapper 还有就是application.yml 还有就是 @MapperScan路径问题

##### 条件构造器（Wrapper，定义 where 条件）
```text
Wrapper  条件构造抽象类
    -- AbstractWrapper 查询条件封装，用于生成 sql 中的 where 语句。
        -- QueryWrapper Entity 对象封装操作类，用于查询。
        -- UpdateWrapper Update 条件封装操作类，用于更新。
    -- AbstractLambdaWrapper 使用 Lambda 表达式封装 wrapper
        -- LambdaQueryWrapper 使用 Lambda 语法封装条件，用于查询。
        -- LambdaUpdateWrapper 使用 Lambda 语法封装条件，用于更新。
```

###### wrapper常用方法
```text
【通用条件：】
【比较大小： ( =, <>, >, >=, <, <= )】
    eq(R column, Object val); // 等价于 =，例: eq("name", "老王") ---> name = '老王'
    ne(R column, Object val); // 等价于 <>，例: ne("name", "老王") ---> name <> '老王'
    gt(R column, Object val); // 等价于 >，例: gt("name", "老王") ---> name > '老王'
    ge(R column, Object val); // 等价于 >=，例: ge("name", "老王") ---> name >= '老王'
    lt(R column, Object val); // 等价于 <，例: lt("name", "老王") ---> name < '老王'
    le(R column, Object val); // 等价于 <=，例: le("name", "老王") ---> name <= '老王'
    
【范围：（between、not between、in、not in）】
   between(R column, Object val1, Object val2); // 等价于 between a and b, 例： between("age", 18, 30) ---> age between 18 and 30
   notBetween(R column, Object val1, Object val2); // 等价于 not between a and b, 例： notBetween("age", 18, 30) ---> age not between 18 and 30
   in(R column, Object... values); // 等价于 字段 IN (v0, v1, ...),例: in("age",{1,2,3}) ---> age in (1,2,3)
   notIn(R column, Object... values); // 等价于 字段 NOT IN (v0, v1, ...), 例: notIn("age",{1,2,3}) ---> age not in (1,2,3)
   inSql(R column, Object... values); // 等价于 字段 IN (sql 语句), 例: inSql("id", "select id from table where id < 3") ---> id in (select id from table where id < 3)
   notInSql(R column, Object... values); // 等价于 字段 NOT IN (sql 语句)
   
【模糊匹配：（like）】
    like(R column, Object val); // 等价于 LIKE '%值%'，例: like("name", "王") ---> name like '%王%'
    notLike(R column, Object val); // 等价于 NOT LIKE '%值%'，例: notLike("name", "王") ---> name not like '%王%'
    likeLeft(R column, Object val); // 等价于 LIKE '%值'，例: likeLeft("name", "王") ---> name like '%王'
    likeRight(R column, Object val); // 等价于 LIKE '值%'，例: likeRight("name", "王") ---> name like '王%'
    
【空值比较：（isNull、isNotNull）】
    isNull(R column); // 等价于 IS NULL，例: isNull("name") ---> name is null
    isNotNull(R column); // 等价于 IS NOT NULL，例: isNotNull("name") ---> name is not null

【分组、排序：（group、having、order）】
    groupBy(R... columns); // 等价于 GROUP BY 字段, ...， 例: groupBy("id", "name") ---> group by id,name
    orderByAsc(R... columns); // 等价于 ORDER BY 字段, ... ASC， 例: orderByAsc("id", "name") ---> order by id ASC,name ASC
    orderByDesc(R... columns); // 等价于 ORDER BY 字段, ... DESC， 例: orderByDesc("id", "name") ---> order by id DESC,name DESC
    having(String sqlHaving, Object... params); // 等价于 HAVING ( sql语句 )， 例: having("sum(age) > {0}", 11) ---> having sum(age) > 11

【拼接、嵌套 sql：（or、and、nested、apply）】
   or(); // 等价于 a or b， 例：eq("id",1).or().eq("name","老王") ---> id = 1 or name = '老王'
   or(Consumer<Param> consumer); // 等价于 or(a or/and b)，or 嵌套。例: or(i -> i.eq("name", "李白").ne("status", "活着")) ---> or (name = '李白' and status <> '活着')
   and(Consumer<Param> consumer); // 等价于 and(a or/and b)，and 嵌套。例: and(i -> i.eq("name", "李白").ne("status", "活着")) ---> and (name = '李白' and status <> '活着')
   nested(Consumer<Param> consumer); // 等价于 (a or/and b)，普通嵌套。例: nested(i -> i.eq("name", "李白").ne("status", "活着")) ---> (name = '李白' and status <> '活着')
   apply(String applySql, Object... params); // 拼接sql（若不使用 params 参数，可能存在 sql 注入），例: apply("date_format(dateColumn,'%Y-%m-%d') = {0}", "2008-08-08") ---> date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")
   last(String lastSql); // 无视优化规则直接拼接到 sql 的最后，可能存若在 sql 注入。
   exists(String existsSql); // 拼接 exists 语句。例: exists("select id from table where age = 1") ---> exists (select id from table where age = 1)
   
【QueryWrapper 条件：】
    select(String... sqlSelect); // 用于定义需要返回的字段。例： select("id", "name", "age") ---> select id, name, age
    select(Predicate<TableFieldInfo> predicate); // Lambda 表达式，过滤需要的字段。
    lambda(); // 返回一个 LambdaQueryWrapper
    
【UpdateWrapper 条件：】
    set(String column, Object val); // 用于设置 set 字段值。例: set("name", null) ---> set name = null
    etSql(String sql); // 用于设置 set 字段值。例: setSql("name = '老李头'") ---> set name = '老李头'
    lambda(); // 返回一个 LambdaUpdateWrapper
```
#### swagger

##### swagger2

引入springfox-swaggger2 跟 springfox-swagger-ui
这里之前引入的是 3.0.0版本的 但是没有成功  后面换成了低版本就可以了
```xml
<!--:)swagger2-生成api文档  -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.8.0</version>
</dependency>
<dependency>
<groupId>io.springfox</groupId>
<artifactId>springfox-swagger-ui</artifactId>
<version>2.8.0</version>
</dependency>
<!--swagger2-生成api文档:~ -->
```
添加配置 参见 SwaggerConfig.java

可访问本地 [swagger-ui-localhost](http://localhost:8080/swagger-ui.html) 
查看接口列表 也可用于测试

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
