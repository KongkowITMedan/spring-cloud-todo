package com.mkdika.cloudtodo.audittrailservice.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public interface OutputChannel {
    @Output
    MessageChannel output();
}
