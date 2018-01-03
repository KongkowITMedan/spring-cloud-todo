package com.mkdika.cloudtodo.msgservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class MsgserviceApplicationTests {
    @Test
    public void applicationStarts() {
        MsgserviceApplication.main(new String[]{"--spring.config.location=classpath:bootstrap-test.properties"});
    }
}
