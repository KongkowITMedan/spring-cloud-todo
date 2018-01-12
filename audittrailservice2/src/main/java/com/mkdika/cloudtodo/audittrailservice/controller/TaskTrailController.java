package com.mkdika.cloudtodo.audittrailservice.controller;

import com.mkdika.cloudtodo.audittrailservice.client.MsgServiceClient;
import com.mkdika.cloudtodo.audittrailservice.message.OutputChannel;
import com.mkdika.cloudtodo.audittrailservice.model.TaskTrail;
import com.mkdika.cloudtodo.audittrailservice.repository.TaskTrailRepository;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@RestController
@RequestMapping("/api/trail")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TaskTrailController {

    @Autowired
    private TaskTrailRepository repository;

    @Autowired
    private MsgServiceClient msgClient;

    @Value("${audittrail.notification.email}")
    private String notificationEmail;
    
    private final OutputChannel channel;
    
    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllTrail() {
        List<TaskTrail> list = (List<TaskTrail>) repository.findAll();
        if (list.size() > 0) {
            return new ResponseEntity(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
   
    @RequestMapping(method = GET, value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTrailByTask(@PathVariable Integer id) throws InterruptedException {
        List<TaskTrail> list = repository.findByTaskId(id);
        return new ResponseEntity(list, HttpStatus.OK);
    }
   
    @RequestMapping(method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAuditTrail(@Valid @RequestBody TaskTrail taskTrail) throws InterruptedException {
         repository.save(taskTrail);
        // if use blocking/synchronous method with Feign client call.
        // msgClient.emailNotification(new EmailDto(notificationEmail, "Task Changed " + String.valueOf(taskTrail.getChangeTime().getTime()), taskTrail.getMessage()));

        // use assynchronous message broker with RabbitMQ
        StringBuilder str = new StringBuilder(notificationEmail);
        str.append(";");
        str.append("Task Changed ");
        str.append(String.valueOf(taskTrail.getChangeTime().getTime()));
        str.append(";");
        str.append(taskTrail.getMessage());              
        Message<String> msg = MessageBuilder.withPayload(str.toString()).build();
        channel.output().send(msg);

        return new ResponseEntity(HttpStatus.OK);
    }
}
