package com.mkdika.cloudtodo.todoservice.model;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public class TaskTests {
    
    @Test
    public void newInstanceNoArgConstructor() {
        Assert.assertNotNull(new Task());
    }
    
    @Test
    public void newInstanceAllArgConstructor() {
        Task task = new Task(6, "Kerja Keras", false,true);
        Assert.assertEquals("Kerja Keras",task.getContent());
    }
    
    @Test
    public void testAssignValue() {
        Task task = new Task();
        task.setId(7);
        task.setContent("Testing Kerja");
        task.setIsComplete(true);
        task.setIsEditable(false);
        Assert.assertFalse(task.getIsEditable());
        Assert.assertTrue(task.getIsComplete());
        Assert.assertNotNull(task.getId());
    }
}
