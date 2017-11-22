package com.mkdika.cloudtodo.todoservice.repository;

import com.mkdika.cloudtodo.todoservice.model.Task;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public interface TaskRepository extends CrudRepository<Task, Integer> {    
}
