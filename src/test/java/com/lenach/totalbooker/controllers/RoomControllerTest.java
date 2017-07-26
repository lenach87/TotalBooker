package com.lenach.totalbooker.controllers;

import com.lenach.totalbooker.config.DataConfig;
import com.lenach.totalbooker.config.JPAConfig;
import com.lenach.totalbooker.config.WebConfig;
import com.lenach.totalbooker.controllers.helpers.TestSecurityConfig;
import com.lenach.totalbooker.controllers.helpers.WithMockCustomUser;
import com.lenach.totalbooker.data.Room;
import com.lenach.totalbooker.repository.RoomRepository;
import com.lenach.totalbooker.service.RoomService;
import com.lenach.totalbooker.service.RoomServiceImpl;
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
@Import({RoomController.class})
public class RoomControllerTest {

    private MockMvc mockMvc;

    @Mock
    RoomRepository roomRepository;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private RoomController roomController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RoomService roomService = new RoomServiceImpl(roomRepository);
        ReflectionTestUtils.setField(roomController, "roomService", roomService);
        this.mockMvc = MockMvcBuilders
//                .standaloneSetup(controller)
                .webAppContextSetup(this.wac)
                .build();
    }

    @Test
    @WithMockCustomUser()
    public void findAll_ShouldReturnAllRoomsRooms() throws Exception {

        LocalDateTime ldt = LocalDateTime.now();

        Room first = new Room(2L, "room1");

        when(roomRepository.findAll()).thenReturn(Arrays.asList(first));
        when(roomRepository.findOne(2L)).thenReturn(first);


        mockMvc.perform(get("/api/v1/rooms"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        verify(roomRepository, times(1)).findAll();
        verifyNoMoreInteractions(roomRepository);
    }

    @Test
    @WithMockCustomUser(username = "user1")
    public void findOne_shouldReturnCorrectRoomById() throws Exception {

        Room room = new Room(2L, "room1");
        room.setId(1L);
        Mockito.when(roomRepository.findOne(2L)).thenReturn(room);
        Room retrieved = roomController.roomById(2L);

        Assert.assertEquals(retrieved.getId(), room.getId());

        mockMvc.perform(get("/api/v1/rooms/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("room1")));
    }

}

