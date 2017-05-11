package com.example;

import com.example.controller.RestControllerWithException;
import com.example.controller.RestControllerWithExceptionHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {RestControllerWithException.class, RestControllerWithExceptionHandler.class})
public class SpringmvcErrorHandleApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTriggerException() throws Exception {
        this.mockMvc.perform(get("/trigger-exception")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.parseMediaType("application/vnd.error")))
                .andExpect(content().json("[{\"logref\":\"exception in RestControllerWithExceptionHandler\",\"message\":\"System error\",\"links\":[]}]"));
    }

    @Test
    public void testTriggerException2() throws Exception {
        this.mockMvc.perform(get("/trigger-exception2")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.parseMediaType("application/vnd.error")))
                .andExpect(content().json("[{\"logref\":\"exception in RestControllerWithException\",\"message\":\"Internal Server Error\",\"links\":[]}]"));
    }

}
