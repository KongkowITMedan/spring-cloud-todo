package com.mkdika.cloudtodo.audittrailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AudittrailserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AudittrailserviceApplication.class, args);
    }
}
