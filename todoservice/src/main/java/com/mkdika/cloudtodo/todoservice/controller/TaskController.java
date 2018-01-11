package com.mkdika.cloudtodo.todoservice.controller;

import com.mkdika.cloudtodo.todoservice.client.AuditTrailServiceClient;
import com.mkdika.cloudtodo.todoservice.model.Task;
import com.mkdika.cloudtodo.todoservice.model.dto.TrailDto;
import com.mkdika.cloudtodo.todoservice.repository.TaskRepository;
import java.util.Date;
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

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllTask() {
        List<Task> list = (List<Task>) repository.findAll();
        if (list.size() > 0) {
            list = list.stream().map(this::addResources)
                    .collect(Collectors.toList());
            return new ResponseEntity(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = GET, value = "/inprogress", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getInprogressTask() {
        List<Task> list = (List<Task>) repository.findByCompleteFalse();
        if (list.size() > 0) {
            list = list.stream().map(this::addResources)
                    .collect(Collectors.toList());
            return new ResponseEntity(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = GET, value = "/finish", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFinishTask() {
        List<Task> list = (List<Task>) repository.findByCompleteTrue();
        if (list.size() > 0) {
            list = list.stream().map(this::addResources)
                    .collect(Collectors.toList());
            return new ResponseEntity(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTaskById(@PathVariable Integer id) {
        Task task = repository.findOne(id);
        if (task != null) {
            addResources(task);
            return new ResponseEntity(task, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = {POST, PUT}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUpdateTask(@Valid @RequestBody Task task) {
        if (task.getTid() != null) {
            Task taskOld = repository.findOne(task.getTid());
            if (taskOld != null && !taskOld.getContent().isEmpty()) {
                trailService.createTaskTrail(new TrailDto(task.getTid(), new Date(), compareTask(taskOld, task)));                
            }
        } 
        repository.save(task);
        addResources(task);
        return new ResponseEntity(task, HttpStatus.OK);
    }

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

    @RequestMapping(method = GET, value = "/{id}/trail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTrail(@PathVariable Integer id) {
        List<TrailDto> trails = trailService.getTaskTrail(id);
        if (trails.size() > 0) {
            return new ResponseEntity(trails, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    private Task addResources(Task task) {
        Link trail = linkTo(TaskController.class)
                .slash(task.getTid())
                .slash("trail")
                .withRel("trail");

        task.add(trail);
        Link self = linkTo(TaskController.class)
                .slash(task.getTid())
                .withSelfRel();
        task.add(self);
        return task;
    }

    private String compareTask(Task taskOld, Task taskNew) {
        StringBuilder sb = new StringBuilder();
        if (taskNew.getContent().compareTo(taskOld.getContent()) != 0) {
            sb.append("Content Changed from ");
            sb.append(taskOld.getContent());
            sb.append(" to ");
            sb.append(taskNew.getContent());
        }

        if (taskNew.getEditable().compareTo(taskOld.getEditable()) != 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("Editable changed from ");
            sb.append(taskOld.getEditable());
            sb.append(" to ");
            sb.append(taskNew.getEditable());
        }

        if (taskNew.getComplete().compareTo(taskOld.getComplete()) != 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("Complete changed from ");
            sb.append(taskOld.getComplete());
            sb.append(" to ");
            sb.append(taskNew.getComplete());
        }
        return sb.toString();
    }
}
