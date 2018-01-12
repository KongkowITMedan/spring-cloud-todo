package com.mkdika.cloudtodo.audittrailservice.repository;

import com.mkdika.cloudtodo.audittrailservice.model.TaskTrail;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public interface TaskTrailRepository extends CrudRepository<TaskTrail, Integer> {   
    
    List<TaskTrail> findByTaskId(Integer taskId);
    
}
