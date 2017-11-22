package com.mkdika.cloudtodo.todoservice.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String content;
    boolean isComplete;
    boolean isEditable;

}
