package com.mkdika.cloudtodo.msgservice.controller;

import com.mkdika.cloudtodo.msgservice.model.EmailMessage;
import com.mkdika.cloudtodo.msgservice.service.EmailService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@RestController
@RequestMapping("/api/msg")
public class MsgController {
    
    @Autowired
    private EmailService emailService;
    
    @ApiOperation(
            value = "Create or Update Task.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = {POST},value = "/email")
    public ResponseEntity sendEmail(@Valid @RequestBody EmailMessage emailMsg) throws EmailException {
        emailService.sendEmail(emailMsg);
        return new ResponseEntity(HttpStatus.OK);
    }    
}
