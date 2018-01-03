package com.mkdika.cloudtodo.todoservice.controller;

import com.mkdika.cloudtodo.todoservice.client.AuditTrailServiceClient;
import com.mkdika.cloudtodo.todoservice.model.Task;
import com.mkdika.cloudtodo.todoservice.model.dto.TrailDto;
import com.mkdika.cloudtodo.todoservice.repository.TaskRepository;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    private AuditTrailServiceClient trailService;

    @ApiOperation(
            value = "Retrieve all Task.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllTask() {
        List<Task> list = (List<Task>) repository.findAll();
        if (list.size() > 0) {
            list = list.stream().map(l -> {
                l.add(linkTo(TaskController.class)
                        .slash(l.getUid())
                        .slash("trail")
                        .withRel("trail"));
                l.add(linkTo(TaskController.class)
                        .slash(l.getUid())
                        .withSelfRel());
                return l;
            }).collect(Collectors.toList());

            return new ResponseEntity(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "Retrieve Task by ID.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTaskById(@PathVariable Integer id) {
        Task task = repository.findOne(id);
        if (task != null) {
            Link trailLink = linkTo(TaskController.class)
                    .slash(task.getUid())
                    .slash("trail")
                    .withRel("trail");
            task.add(trailLink);
            return new ResponseEntity(task, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "Create or Update Task.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = {POST, PUT}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUpdateTask(@Valid @RequestBody Task task) {
        if (task.getUid() != null) {
            Task taskOld = repository.findOne(task.getUid());
//            trailService.createTaskTrail(new TrailDto(task.getUid(), new Date(), compareOldNewTask(taskOld.get(), task)));
        }
        repository.save(task);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete Task by ID.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        Task task = repository.findOne(id);
        if (task != null) {
            repository.delete(task);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(
            value = "Retrieve all Trail by Task ID.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = GET, value = "/{id}/trail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTrail(@PathVariable Integer id) {
        List<TrailDto> trails = trailService.getTaskTrail(id);
        if (trails.size() > 0) {
            return new ResponseEntity(trails, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    private String compareOldNewTask(Task taskOld, Task taskNew) {
        StringBuilder sb = new StringBuilder();
        if (taskNew.getContent().compareTo(taskOld.getContent()) != 0) {
            sb.append("Content Changed from ");
            sb.append(taskOld.getContent());
            sb.append(" to ");
            sb.append(taskNew.getContent());
        }

        if (taskNew.getIsEditable().compareTo(taskOld.getIsEditable()) != 0) {
            sb.append(", Editable changed from ");
            sb.append(taskOld.getIsEditable());
            sb.append(" to ");
            sb.append(taskNew.getIsEditable());
        }

        if (taskNew.getIsComplete().compareTo(taskOld.getIsComplete()) != 0) {
            sb.append(", Complete changed from ");
            sb.append(taskOld.getIsComplete());
            sb.append(" to ");
            sb.append(taskNew.getIsComplete());
        }
        return sb.toString();
    }
}
