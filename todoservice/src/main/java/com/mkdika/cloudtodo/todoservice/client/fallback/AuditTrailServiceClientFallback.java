package com.mkdika.cloudtodo.todoservice.client.fallback;

import com.mkdika.cloudtodo.todoservice.client.AuditTrailServiceClient;
import com.mkdika.cloudtodo.todoservice.model.dto.TrailDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@Component
public class AuditTrailServiceClientFallback implements AuditTrailServiceClient{

    @Override
    public List<TrailDto> getTaskTrail(Integer id) {
        System.out.println(">>>> GET task trail failed fallback methods!!!");
        List<TrailDto> list = new ArrayList<>();
        list.add(new TrailDto(9999,new Date(),"This is fallback!"));
        return list;        
    }

    @Override
    public void createTaskTrail(TrailDto trailDto) {
        System.out.println(">>>> CREATE task trail failed fallback methods!!!");
    }    
}
