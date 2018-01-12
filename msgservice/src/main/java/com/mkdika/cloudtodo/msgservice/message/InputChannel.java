package com.mkdika.cloudtodo.msgservice.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public interface InputChannel {
    
    @Input
    SubscribableChannel input();
    
}
