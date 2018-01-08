package com.mkdika.cloudtodo.audittrailservice.model.dto;

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
public class EmailDto {
    String toAddress;
    String subject;
    String content;
}
