package com.mkdika.cloudtodo.todoservice.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task extends ResourceSupport implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer uid;
    
    String content;
    Boolean isComplete;
    Boolean isEditable;           
}
