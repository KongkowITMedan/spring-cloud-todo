package com.mkdika.cloudtodo.audittrailservice.client.fallback;

import com.mkdika.cloudtodo.audittrailservice.client.MsgServiceClient;
import com.mkdika.cloudtodo.audittrailservice.model.dto.EmailDto;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@Component
public class MsgServiceClientFallback implements MsgServiceClient {

    @Override
    public void emailNotification(EmailDto email) {
        System.out.println(">>>> CREATE email notification failed fallback methods!!!");
    }
    
}
