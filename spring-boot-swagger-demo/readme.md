Error creating bean with name 'apiDocumentationScanner' defined in URL

pom.xml 中多了一个依赖

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
```

启动成功后
> 启动项目后打开
http://127.0.0.1:8080/v3/api-docs
能看到 json 格式的接口描述
http://127.0.0.1:8080/swagger-ui/index.html
查看 ui 界面
> http://localhost:8080/doc.html
> knife4j 新界面 可用于导出Markdown HTML word openapi