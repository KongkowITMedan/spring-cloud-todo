package com.mkdika.cloudtodo.audittrailservice;

import com.mkdika.cloudtodo.audittrailservice.message.OutputChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;


@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
@EnableBinding(OutputChannel.class)
public class AudittrailserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AudittrailserviceApplication.class, args);
    }
}
