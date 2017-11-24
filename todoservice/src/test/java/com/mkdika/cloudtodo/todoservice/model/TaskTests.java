package com.mkdika.cloudtodo.todoservice.model;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public class TaskTests {

    @Test
    public void newInstanceNoArgConst() {
        Assert.assertNotNull(new Task());
    }

    @Test
    public void newInstanceArgConst() {
        Task task = new Task(6, "Kerja Keras", false, true);
        Assert.assertEquals("Kerja Keras", task.getContent());
    }

    /*
        Unit Test for Lombok @Data annotation to be coveraged all.
        @Data is consist of :
        - @Setter
        - @Getter
        - @ToString
        - @EqualsAndHashCode                
     */
    @Test
    public void testDataEntity() {
        Task task = new Task();
        task.setId(7);
        task.setContent("Testing Kerja");
        task.setIsComplete(true);
        task.setIsEditable(false);
        Assert.assertNotNull(task.getId());
        Assert.assertEquals("Testing Kerja", task.getContent());
        Assert.assertFalse(task.getIsEditable());
        Assert.assertTrue(task.getIsComplete());
        
        Assert.assertNotNull(task.toString());
        Assert.assertNotNull(task.hashCode());
        Assert.assertFalse(task.equals(new Task()));        
    }
}
