package com.lenach.totalbooker.controllers;

import com.lenach.totalbooker.config.DataConfig;
import com.lenach.totalbooker.config.JPAConfig;
import com.lenach.totalbooker.config.WebConfig;
import com.lenach.totalbooker.controllers.helpers.TestSecurityConfig;
import com.lenach.totalbooker.controllers.helpers.WithMockCustomUser;
import com.lenach.totalbooker.controllers.mappers.BookingMapper;
import com.lenach.totalbooker.controllers.mappers.BookingMapperImpl;
import com.lenach.totalbooker.controllers.viewmodel.BookingViewModel;
import com.lenach.totalbooker.data.Booking;
import com.lenach.totalbooker.repository.BookingRepository;
import com.lenach.totalbooker.service.BookingService;
import com.lenach.totalbooker.service.BookingServiceImpl;
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

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, DataConfig.class, JPAConfig.class, TestSecurityConfig.class})
@Import({BookingMapperImpl.class, BookingController.class})
public class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    BookingRepository bookingRepository;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private BookingController bookingController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        BookingService bookingService = new BookingServiceImpl(bookingRepository);
        ReflectionTestUtils.setField(bookingController, "bookingService", bookingService);
        this.mockMvc = MockMvcBuilders
//                .standaloneSetup(controller)
                .webAppContextSetup(this.wac)
                .build();
    }

    @Test
    @WithMockCustomUser()
    public void findAll_ShouldAddBookingEntriesToModelAndRenderBookingsListView() throws Exception {

        LocalDateTime ldt = LocalDateTime.now();

        Booking first = new Booking(2L, 1L, ldt, 60L);

        when(bookingRepository.findAll()).thenReturn(Arrays.asList(first));
        when(bookingRepository.findOne(4L)).thenReturn(first);


        mockMvc.perform(get("/api/v1/bookings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        verify(bookingRepository, times(1)).findAll();
        verifyNoMoreInteractions(bookingRepository);
    }

    @Test
    @WithMockCustomUser(username = "user1")
    public void addBooking_shouldCreateNewBookingWithSecurityUser() throws Exception {

        LocalDateTime ldt = LocalDateTime.parse("2011-01-24T22:08:41.079");
        Booking booking = new Booking(2L, 1L, ldt, 60L );

        mockMvc.perform(post("/api/v1/bookings").contentType("application/json").content(" {\n" +
                "    \"roomId\": 2,\n" +
                "    \"bookingTimeStart\": \"2011-01-24T22:08:41.079\",\n" +
                "    \"bookingDuration\": 60\n" +
                "  }"))
                .andExpect(status().isCreated());

        BookingMapper mapper = new BookingMapperImpl();
        Mockito.when(bookingRepository.findOne(4L)).thenReturn(booking);
        BookingViewModel retrieved = bookingController.bookingById(4L);
        BookingViewModel initial = mapper.bookingToBookingModel(booking);

        Assert.assertEquals(retrieved.getId(), initial.getId());

        mockMvc.perform(get("/api/v1/bookings/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId", is (2)))
                .andExpect(jsonPath("$.userId", is (1)));
    }

    @Test
    @WithMockCustomUser(username = "user1")
    public void findOne_shouldReturnCorrectBooking() throws Exception {

        LocalDateTime ldt = LocalDateTime.parse("2011-01-24T22:08:41.079");
        Booking booking = new Booking(2L, 1L, ldt, 60L );

        BookingMapper mapper = new BookingMapperImpl();
        Mockito.when(bookingRepository.findOne(4L)).thenReturn(booking);
        BookingViewModel retrieved = bookingController.bookingById(4L);
        BookingViewModel initial = mapper.bookingToBookingModel(booking);

        Assert.assertEquals(retrieved.getId(), initial.getId());

        mockMvc.perform(get("/api/v1/bookings/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId", is (2)))
                .andExpect(jsonPath("$.userId", is (1)));
    }

}

