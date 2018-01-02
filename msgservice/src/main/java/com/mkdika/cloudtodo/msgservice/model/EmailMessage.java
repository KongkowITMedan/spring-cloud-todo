package com.mkdika.cloudtodo.msgservice.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage implements Serializable {
    
    String toAddress;
    String subject;
    String content;

}
