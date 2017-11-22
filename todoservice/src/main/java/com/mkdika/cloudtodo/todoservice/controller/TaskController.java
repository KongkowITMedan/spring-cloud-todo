package com.mkdika.cloudtodo.todoservice.controller;

import com.mkdika.cloudtodo.todoservice.model.Task;
import com.mkdika.cloudtodo.todoservice.repository.TaskRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@RestController
@RequestMapping("/api/todo")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @RequestMapping(method = GET)
    public ResponseEntity getAllTask() {
        List<Task> list = (List<Task>) repository.findAll();
        if (list.size() > 0) {
            return new ResponseEntity(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
