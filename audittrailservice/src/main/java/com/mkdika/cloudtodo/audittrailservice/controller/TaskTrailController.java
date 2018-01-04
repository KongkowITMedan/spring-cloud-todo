package com.mkdika.cloudtodo.audittrailservice.controller;

import com.mkdika.cloudtodo.audittrailservice.model.TaskTrail;
import com.mkdika.cloudtodo.audittrailservice.repository.TaskTrailRepository;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@RestController
@RequestMapping("/api/trail")
@CrossOrigin(origins = "http://localhost:8080")
public class TaskTrailController {
        
    @Autowired
    private TaskTrailRepository repository;

    @ApiOperation(
            value = "Retrieve all audit trail data.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllTrail() {
        List<TaskTrail> list = (List<TaskTrail>) repository.findAll();
        if (list.size() > 0) {
            return new ResponseEntity(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @ApiOperation(
            value = "Retrieve all audit trail data.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = GET, value="/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTrailByTask(@PathVariable Integer id) {
        List<TaskTrail> list = repository.findByTaskId(id);
        return new ResponseEntity(list, HttpStatus.OK);        
    }   
    
    @ApiOperation(
            value = "Create new audit trail log.",
            notes = "Not available.",
            produces = "application/json")
    @RequestMapping(method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAuditTrail(@Valid @RequestBody TaskTrail taskTrail) {
        repository.save(taskTrail);
        return new ResponseEntity(HttpStatus.OK);
    }
}
