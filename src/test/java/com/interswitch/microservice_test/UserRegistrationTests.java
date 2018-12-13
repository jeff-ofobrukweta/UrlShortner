package com.interswitch.microservice_test;

import com.interswitch.microservice_test.UserModelRepository.UserModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    UserModelRepository userModelRepository;

    @Test
    public void exampleTest() throws Exception {
        when(userModelRepository.findAll()).thenReturn(
                Collections.emptyMap()
        );
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/all")
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(mvcResult.getResponse());
//        verify(userModelRepository.findAll());
    }

}
