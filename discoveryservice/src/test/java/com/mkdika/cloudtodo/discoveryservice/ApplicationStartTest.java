package com.mkdika.cloudtodo.discoveryservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ApplicationStartTest {

    @Test
    public void applicationStarts() {
        DiscoveryserviceApplication.main(new String[]{"--spring.config.location=classpath:bootstrap-test.properties"});
    }
}
