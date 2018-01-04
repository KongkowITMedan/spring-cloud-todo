package com.mkdika.cloudtodo.todoservice.controller.resource;

import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public class GenericResource<T> extends ResourceSupport{
    
    private T data;

    public GenericResource(T data) {
        this.data = data;
    }            
}
