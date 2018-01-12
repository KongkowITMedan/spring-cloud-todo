package com.mkdika.cloudtodo.msgservice.message;

import com.mkdika.cloudtodo.msgservice.model.EmailMessage;
import com.mkdika.cloudtodo.msgservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@Component
public class ReceiveNotice {
    
    @Autowired
    private EmailService emailService;
    
    // listen & receive emailNotice from message broker (rabbitmq)
    @StreamListener(Sink.INPUT)
    public void noticeEmail(String msg) throws Exception {
        if (msg != null && msg.length()>0) {
            System.out.println(">>>> RECEIVE: " + msg);
            String[] arrs = msg.split(";");
            emailService.sendEmail(new EmailMessage(arrs[0],arrs[1],arrs[2]));
        }                                
    }    
}
