package com.mkdika.cloudtodo.msgservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsgserviceApplication {  
    public static void main(String[] args) {
        SpringApplication.run(MsgserviceApplication.class, args);
    }   
}
