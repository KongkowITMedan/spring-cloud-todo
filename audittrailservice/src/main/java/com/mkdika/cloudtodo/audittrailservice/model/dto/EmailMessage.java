package com.mkdika.cloudtodo.audittrailservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EmailMessage {
    String toAddress;
    String subject;
    String content;
}
