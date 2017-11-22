package com.mkdika.cloudtodo.todoservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkdika.cloudtodo.todoservice.TodoserviceApplication;
import com.mkdika.cloudtodo.todoservice.model.Task;
import com.mkdika.cloudtodo.todoservice.repository.TaskRepository;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoserviceApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskControllerTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private ObjectMapper mapper;

    @Autowired
    private TaskRepository repository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .build();
        mapper = new ObjectMapper();
    }

    @Test
    public void test01TotalInitTask() throws Exception {
        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.*", hasSize(5)));
    }

    @Test
    public void test02FindById() throws Exception {
        mockMvc.perform(get("/api/todo/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.content", is("Bayar Indihome")));
        
        
    }
    
    @Test
    public void test03FindByIdNull() throws Exception {
         mockMvc.perform(get("/api/todo/10"))
                    .andExpect(status().is4xxClientError());
    }
    
    @Test
    public void test04CreateTask() throws Exception {
        String custJson = json(new Task(7, "Belajar Renang", false,true));
        this.mockMvc.perform(post("/api/todo")
                .contentType(contentType)
                .content(custJson))
                .andExpect(status().isOk());        
    }
    
    
    @Test
    public void test05DeleteTask() throws Exception {
        mockMvc.perform(delete("/api/todo/5"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void test06DeleteTaskError() throws Exception {
        mockMvc.perform(delete("/api/todo/10"))
                    .andExpect(status().is4xxClientError());
    }

    @Test
    public void test10TotalAfterDeleteAll() throws Exception {
        repository.deleteAll();
        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isNotFound());
    }
    
    protected String json(Task c) throws IOException {
        return mapper.writeValueAsString(c);
    }
}
