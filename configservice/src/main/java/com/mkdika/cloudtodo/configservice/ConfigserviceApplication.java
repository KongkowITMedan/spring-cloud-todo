package com.mkdika.cloudtodo.configservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigserviceApplication.class, args);
    }
}
