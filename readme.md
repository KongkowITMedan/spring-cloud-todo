## [Spring](https://spring.io/) Cloud Todo Apps
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](/LICENSE)
[![Build Status](https://travis-ci.org/KongkowITMedan/spring-cloud-todo.svg?branch=master)](https://travis-ci.org/KongkowITMedan/spring-cloud-todo)
[![codecov](https://codecov.io/gh/KongkowITMedan/spring-cloud-todo/branch/master/graph/badge.svg)](https://codecov.io/gh/KongkowITMedan/spring-cloud-todo)
[![codebeat badge](https://codebeat.co/badges/74889632-eba3-41bc-9c6f-9d68db38c2a1)](https://codebeat.co/projects/github-com-kongkowitmedan-spring-cloud-todo-master)


These are the backend services for [Vue Spring Cloud Todo](https://github.com/mkdika/vue-todo).

### Framework & Libraries
- [Java 8 (JDK 8u131 or above)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)


### Local Settings
- Export OS level Environment variable:
	- `TODO_EMAIL_USERNAME` , email account username for send email notification.
	- `TODO_EMAIL_PASSWORD` , email account password for send email notification.
- Other Email Settings:
	- Set the email config in `msg-service.properties` file, at `#todo email client setup` section.
	

### Local URL & API Test
- __Discovery Service__:
	- [Web Panel](http://localhost:8761/)
	
- __Todo Service__:	
	- __Swagger:__
    	- [API-Docs](http://localhost:8121/v2/api-docs)
    	- [Web UI](http://localhost:8121/swagger-ui.html)
	- __H2:__
    	- [Web Console](http://localhost:8121/h2-console/)    
- __Vue Todo__ - Front End:
	- [README](vue-todo/README.md)


### References
- Spring Cloud
	- [Spring Cloud PiggyMetrics](https://github.com/sqshq/PiggyMetrics)
- Spring AMQP
	- [RabbitMQ Message Dispatching with Spring AMQP](http://www.baeldung.com/rabbitmq-spring-amqp)	
	- [Event-Driven Microservices Using Spring Cloud Stream and RabbitMQ](https://dzone.com/articles/event-driven-microservices-using-spring-cloud-stre)
- Spring Data & Elasticsearch:
	- [The Persistence Layer with Spring Data JPA](http://www.baeldung.com/the-persistence-layer-with-spring-data-jpa)
	- [Introduction to Spring Data Elasticsearch](http://www.baeldung.com/spring-data-elasticsearch-tutorial)
	- [First Step with Spring Boot and Elasticsearch](https://dzone.com/articles/first-step-spring-boot-and)
