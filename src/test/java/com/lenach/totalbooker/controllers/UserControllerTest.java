package com.lenach.totalbooker.controllers;

import com.lenach.totalbooker.config.DataConfig;
import com.lenach.totalbooker.config.JPAConfig;
import com.lenach.totalbooker.config.WebConfig;

import com.lenach.totalbooker.controllers.helpers.TestSecurityConfig;
import com.lenach.totalbooker.controllers.helpers.WithMockCustomUser;
import com.lenach.totalbooker.data.User;
import com.lenach.totalbooker.repository.UserRepository;
import com.lenach.totalbooker.service.UserService;
import com.lenach.totalbooker.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, DataConfig.class, JPAConfig.class, TestSecurityConfig.class})
@Import({UserController.class})
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    UserRepository userRepository;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        UserService userService = new UserServiceImpl(userRepository);
        ReflectionTestUtils.setField(userController, "userService", userService);
        this.mockMvc = MockMvcBuilders
//                .standaloneSetup(controller)
                .webAppContextSetup(this.wac)
                .build();
    }

    @Test
    @WithMockCustomUser()
    public void findAll_ShouldReturnAllUsers() throws Exception {

        LocalDateTime ldt = LocalDateTime.now();

        User first = new User("user1", "password");

        when(userRepository.findAll()).thenReturn(Arrays.asList(first));
        when(userRepository.findOne(1L)).thenReturn(first);


        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @WithMockCustomUser(username = "user1")
    public void findOne_shouldReturnCorrectUserById() throws Exception {

        User user = new User("user1", "password");
        user.setId(1L);
        Mockito.when(userRepository.findOne(1L)).thenReturn(user);
        User retrieved = userController.userById(1L);

        Assert.assertEquals(retrieved.getId(), user.getId());

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is ("user1")))
                .andExpect(jsonPath("$.password", is ("password")));
    }

}

