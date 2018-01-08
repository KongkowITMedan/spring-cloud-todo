package com.mkdika.cloudtodo.audittrailservice.client;

import com.mkdika.cloudtodo.audittrailservice.model.dto.EmailDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@FeignClient(name = "msg-service")
public interface MsgServiceClient {
    
    @RequestMapping(method = RequestMethod.POST,
            value = "/api/msg/email",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void emailNotification(@RequestBody EmailDto email);
    
}
