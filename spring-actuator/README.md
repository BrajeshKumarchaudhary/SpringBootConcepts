### Springboot Actuator
```text
 -It provide several features like health check-up, auditing JVM metrics, log information, caching statics, etc.
 -Actuator also make it easy to integrate with external monitoring systems like Prometheus, Graphite, DataDog, New Relic, etc. 

```

## Enabling Actuator to an existing application
```text
 -To enable these features to an existing application, we need to add spring-boot-starter-actuator in the pom.xml file
    <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>

```

##
```text
 -We can enable access to all the endpoints using application.properties
 *we can access All our actuator endpoints without requiring authentication
    management.endpoints.web.exposure.include=*
``` 

```text
Let’s start our application to see the actuator  in action.Once you start the application open the following URL in your browser https://localhost:8080//actuator .Make sure to change the URL based on your application configurations:
[Default Actuator URl]
 -https://localhost:8080//actuator
[health endpoint]
 -http://localhost:8080/actuator/health
```

## Enable / Disable Actuator Endpoints
```text
   *Add This in properties file
   management.endpoint.shutdown.enabled=true
```

## To include / exclude the endpoint over HTTP, we should configure the following properties through the application.properties
```text
management.endpoints.web.exposure.include=health,info 
management.endpoints.web.exposure.exclude=
```
## /health Endpoint

```text
The /health endpoint is to check the health or state of the running application. With standard setup this endpoint only display a simple status of the application like up or DOWN.This endpoint is also capable of provide you more information related to application health like.

To enable these details, add the following property in the application.properties file.

*management.endpoint.health.show-details=always
```


## Creating a Custom Endpoint

```text
  -Spring Boot provides an easy way to create custom endpoints.Spring Boot 2.x introduced @Endpoint annotation.
  -The @Endpoint annotation can be combined with @ReadOperation,@WriteOperation and @DeleteOperation to develop endpoints.
  -[ custom endpoint]()
```

## Extending Existing Endpoints
[CustomHealthIndicator]()

## Additional Customization
```text
Spring Boot Actuator provides several options for the additional customization using application.properties file. Below are some important properties to customize the Actuator.
#Disable security feature for all end points.By default all sensitive HTTP endpoints are secure.
management.security.enabled=false

#Customizing the management endpoint paths.All endpoints will be accessible through manage/endpoint
management.context-path=/manage

#Property to configure /change management server port.All endpoints will be accessible only through this port
management.server.port=8081

#customize the address that the management endpoints. 
management.server.address=127.0.0.1

#disable endpoint over HTTP
management.port=-1

```

## Securing Actuator Endpoints

```text
   -Spring boot actuator provides several endpoints with sensitive data and we must secure these endpoints on the production environment to avoid exposing unauthorized access to this data. In case Spring security configured for our project, Spring boot by default secure these endpoints by enabling an HTTP based authentication. We can easily enable this security by adding spring security in our application.
  
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-security</artifactId>
</dependency>

# Spring Security Default user name and password
spring.security.user.name=admin
spring.security.user.password=nimda
spring.security.user.roles=ADMIN

```



### Spring Boot Actuator with Prometheus

```text
  - Monitoring the application health is important on the production environment. This help us notice any error or performance issue with the application.
 
# Prometheus
 -A multi-dimensional data model with time series data identified by metric name and key/value pairs
 -PromQL, a flexible query language to leverage this dimensionality
 -No reliance on distributed storage; single server nodes are autonomous
 -Time series collection happens via a pull model over HTTP
 -Pushing time series supported via an intermediary gateway
 -Targets discovered via service discovery or static configuration
 -Multiple modes of graphing and dash boarding support.
 
```

## Add Prometheus Registry
```text
  <!-- Micrometer Prometheus registry  -->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

![Prometheus](https://github.com/BrajeshKumarchaudhary/SpringBootConcepts/blob/master/spring-actuator/p.png)

---




