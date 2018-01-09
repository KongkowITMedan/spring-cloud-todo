package com.mkdika.cloudtodo.msgservice.service;

import com.mkdika.cloudtodo.msgservice.model.EmailMessage;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@Service
@RefreshScope
public class EmailService {

    @Value("${todo.email.host}")
    private String emailHost;

    @Value("${todo.email.port}")
    private int emailPort;

    @Value("${todo.email.from}")
    private String emailFrom;

    @Value("${todo.email.username}")
    private String emailUsername;

    @Value("${todo.email.password}")
    private String emailPassword;

    private Email email;

    public boolean sendEmail(EmailMessage emailMsg) throws EmailException {
        
        System.out.println(">>> Port: " + emailPort);
        System.out.println(">>> Host: " + emailHost);
        System.out.println(">>> From: " + emailFrom);

        email = new SimpleEmail();
        email.setAuthenticator(new DefaultAuthenticator(emailUsername, emailPassword));
        email.setDebug(true);
        email.setHostName(emailHost);
        email.setSmtpPort(emailPort);
        email.setSSLOnConnect(true);
        email.setFrom(emailUsername, "SysbotTodo");

        email.addTo(emailMsg.getToAddress());
        email.setSubject(emailMsg.getSubject());
        email.setMsg(emailMsg.getContent());
        email.send();
        System.out.println(">>>>>>>>>> Mail to '" + emailMsg.getToAddress() + "' has been sent!");
        return true;
    }
}
