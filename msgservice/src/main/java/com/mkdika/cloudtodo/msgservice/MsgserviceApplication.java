package com.mkdika.cloudtodo.msgservice;

import com.mkdika.cloudtodo.msgservice.message.InputChannel;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(InputChannel.class)
public class MsgserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsgserviceApplication.class, args);
    }
}
