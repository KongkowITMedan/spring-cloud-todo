## [Spring](https://spring.io/) Cloud Todo Apps
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](/LICENSE)
[![Build Status](https://travis-ci.org/KongkowITMedan/spring-cloud-todo.svg?branch=master)](https://travis-ci.org/KongkowITMedan/spring-cloud-todo)
[![codecov](https://codecov.io/gh/KongkowITMedan/spring-cloud-todo/branch/master/graph/badge.svg)](https://codecov.io/gh/KongkowITMedan/spring-cloud-todo)
[![codebeat badge](https://codebeat.co/badges/74889632-eba3-41bc-9c6f-9d68db38c2a1)](https://codebeat.co/projects/github-com-kongkowitmedan-spring-cloud-todo-master)

These are the backend micro-services for [Vue Spring Cloud Todo](https://github.com/mkdika/vue-todo).

### Prerequire & Library
	- [Java 8 (JDK 8u131 or above)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
	- [Maven3](https://maven.apache.org/index.html)
	- [RabbitMQ](https://www.rabbitmq.com/)
	- [NetBeans IDE 8.2](https://netbeans.org/) (optional)

### Local Settings
- Export OS level Environment variable:
	- `TODO_EMAIL_USERNAME` , email account username for send email notification.
	- `TODO_EMAIL_PASSWORD` , email account password for send email notification.
- Other Email Settings:
	- Set the email config in `msg-service.properties` file, at `#todo email client setup` section.
- __Vue Todo__ - Front End:
	- To run & build front end app, please refer to this [README](vue-todo/README.md).

### Install & Run Application
- Running the service Order:
	1. `configservice`
	2. `discoveryservice`
	3. `gatewayservice`
	4. `msgservice`
	5. `audittrailservice`	
	6. `todoservice`
	7. `Monitoringservice`	


### Local Endpoints & API Test
- __Discovery Service__:
	- [Web Panel](http://localhost:8761/), view eureka front page.
	
- __Monitoring Service__:
	- [Todo Service Hystrix Dashboard](http://localhost:8100/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8121%2Fhystrix.stream&delay=1000&title=TodoService)
	- [AudiTrail Service Hystrix Dashboard](http://localhost:8100/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8122%2Fhystrix.stream&delay=1000&title=AudiTrailService)
	- [Turbine Dashboard](http://localhost:8100/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8100%2Fturbine.stream&delay=100&title=TurbineMonitor)
	
- __Todo Service__ (and other related services):	
	- __Spring Boot Production Ready Endpoints:__
		- [Information](http://localhost:8121/info), displays application information.
		- [Environment](http://localhost:8121/env), displays environment properties from Spring.
		- [Health](http://localhost:8121/health), shows app health information.
		- [Mappings](http://localhost:8121/mappings), displays mapped web end-point.
		- [Beans](http://localhost:8121/beans), displays a complete list of all Spring beans within app.
		- [Config Properties](http://localhost:8121/configprops), displays a collated list of all `@ConfigurationProperties`
		- [Dump](http://localhost:8121/dump), displays and Performs a thread dump
		- [Metrics](http://localhost:8121/metrics), shows `metrics` information for the current app.
		- [Trace](http://localhost:8121/trace), displays last 100 HTTP requests.
	- __Swagger:__
    	- [API-Docs](http://localhost:8121/v2/api-docs)
    	- [Web UI](http://localhost:8121/swagger-ui.html), view registered web end-point.
	- __H2:__
    	- [Web Console](http://localhost:8121/h2-console/), open H2 web console.
    	
- __RabbitMQ__:
	- [RabbitMQ Management](http://localhost:15672), default login/password: `guest/guest`.

	
### Images & Graph
- Hystrix Dashboard Diagram Information
![Imgur](https://i.imgur.com/NESKMta.png)	


### Online Demo
coming soon...


### Documentation
- [KIT Medan #7 Meetup Slide]()


### References
- Spring Boot
	- [Intro to Spring Boot](http://www.baeldung.com/spring-boot-start)
	- [Spring Boot Production-ready Features Endpoints](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html)
	- [Enabling Cross Origin Requests for a RESTful Web Service](https://spring.io/guides/gs/rest-service-cors/)	
- Spring Cloud
	- [Introducing Spring Cloud](https://spring.io/blog/2014/06/03/introducing-spring-cloud)
	- [Spring Cloud PiggyMetrics - Example](https://github.com/sqshq/PiggyMetrics)
- Spring Cloud Config
	- [Microservices Configuration With Spring Cloud Config](https://piotrminkowski.wordpress.com/2017/07/17/microservices-configuration-with-spring-cloud-config/)
	- [Refreshable Configuration using Spring Cloud Config Server](http://tech.asimio.net/2017/02/02/Refreshable-Configuration-using-Spring-Cloud-Config-Server-Spring-Cloud-Bus-RabbitMQ-and-Git.html)
- Spring Cloud Hystrix
	- [A Guide to Spring Cloud Netflix Hystrix](http://www.baeldung.com/spring-cloud-netflix-hystrix)
	- [Spring Cloud Hystrix example](https://exampledriven.wordpress.com/2016/07/05/spring-cloud-hystrix-example/)
	- [CircuitBreaker Pattern](https://martinfowler.com/bliki/CircuitBreaker.html)
- Spring Cloud Ribbon
	- [Introduction to Spring Cloud Rest Client with Netflix Ribbon](http://www.baeldung.com/spring-cloud-rest-client-with-netflix-ribbon)
- Spring AMQP
	- [RabbitMQ Message Dispatching with Spring AMQP](http://www.baeldung.com/rabbitmq-spring-amqp)	
	- [Event-Driven Microservices Using Spring Cloud Stream and RabbitMQ](https://dzone.com/articles/event-driven-microservices-using-spring-cloud-stre)
- Spring Cloud Stream	
	- [Introduction to Spring Cloud Stream](http://www.baeldung.com/spring-cloud-stream)
	- [Spring Cloud Stream Reference Guide](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/)
- Spring Data & Elasticsearch:
	- [The Persistence Layer with Spring Data JPA](http://www.baeldung.com/the-persistence-layer-with-spring-data-jpa)
	- [Introduction to Spring Data Elasticsearch](http://www.baeldung.com/spring-data-elasticsearch-tutorial)
	- [First Step with Spring Boot and Elasticsearch](https://dzone.com/articles/first-step-spring-boot-and)
