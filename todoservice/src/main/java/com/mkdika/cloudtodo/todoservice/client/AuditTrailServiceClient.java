package com.mkdika.cloudtodo.todoservice.client;

import com.mkdika.cloudtodo.todoservice.model.dto.TrailDto;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@FeignClient(name = "audittrail-service")
public interface AuditTrailServiceClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "/api/trail/task/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<TrailDto> getTaskTrail(@PathVariable("id") Integer id);

}
