# Swagger Aggregation

为你快速聚合散落在系统各处的 swagger 文档。

## 版本建议

- `springfox-swagger` >= 2.9
- `spring-cloud-gateway` >= 2.2.0

### 外部资源

- `swagger.json` >= 2.0

## 如何使用

> 此项目暂时未发布到 maven central，请先通过 clone 源码到本地执行 mvn install 使用 

引入依赖

```xml
<dependency>
    <groupId>com.github.taccisum</groupId>
    <artifactId>swagger-aggregation-starter</artifactId>
    <version>{version}</version>
</dependency>
```

配置你的 swagger 文档资源

```properties
# application.properties
swagger.aggregation.resources[0].name=custom-service
swagger.aggregation.resources[0].url=/custom_location
```

启动你的应用，访问聚合页面

    $ open localhost:8080/swagger-ui.html

### 整合 Spring Cloud Gateway

引入依赖

```xml
<dependency>
    <groupId>com.github.taccisum</groupId>
    <artifactId>swagger-aggregation-sc-gateway-support</artifactId>
    <version>{version}</version>
</dependency>
```

启动你的应用，访问聚合页面

    $ open localhost:8080/swagger-ui.html

若注册中心中有注册其它服务，其 swagger 文档会自动展示在聚合页面中。

### 示例工程

https://github.com/taccisum/swagger-aggregation-sample



