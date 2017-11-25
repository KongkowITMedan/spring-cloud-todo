package com.mkdika.cloudtodo.todoservice.model.dto;

import java.util.Date;
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
public class TrailDto {
    Integer taskId;
    Date changeTime;
    String message;
}
