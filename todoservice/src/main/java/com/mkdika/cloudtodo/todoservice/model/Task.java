package com.mkdika.cloudtodo.todoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Column(name = "id")
    @JsonProperty("id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    Integer tid;
    
    String content;
    Boolean complete;
    Boolean editable;            
}
