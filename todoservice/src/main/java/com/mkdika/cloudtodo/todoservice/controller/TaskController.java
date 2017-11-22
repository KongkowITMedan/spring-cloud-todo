package com.mkdika.cloudtodo.todoservice.controller;

import com.mkdika.cloudtodo.todoservice.model.Task;
import com.mkdika.cloudtodo.todoservice.repository.TaskRepository;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
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

    @ApiOperation(
            value = "Retrieve all Task.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = GET)
    public ResponseEntity getAllTask() {
        List<Task> list = (List<Task>) repository.findAll();
        if (list.size() > 0) {
            return new ResponseEntity(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "Retrieve Task by ID.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity getTaskById(@PathVariable Integer id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            return new ResponseEntity(task, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "Create or Update Task.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = {POST, PUT})
    public ResponseEntity addUpdateTask(@Valid @RequestBody Task task) {
        repository.save(task);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete Task by ID.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = DELETE, value = "/{id}")
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            repository.delete(task.get());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
