package com.lenach.totalbooker.controllers;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import com.lenach.totalbooker.config.*;
import com.lenach.totalbooker.controllers.mappers.BookingMapper;
import com.lenach.totalbooker.controllers.mappers.BookingMapperImpl;
import com.lenach.totalbooker.controllers.viewmodel.CreateBooking;
import com.lenach.totalbooker.data.Booking;
import com.lenach.totalbooker.repository.BookingRepository;
import com.lenach.totalbooker.security.BookingUserDetailsService;
import com.lenach.totalbooker.service.BookingService;
import com.lenach.totalbooker.service.BookingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Created by o.chubukina on 10/05/2017.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class, WebConfig.class, DataConfig.class, JPAConfig.class, SecurityConfig.class})
@Import(BookingMapperImpl.class)
public class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    BookingRepository bookingRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    UserDetailsService bookingUserDetailsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        BookingService bookingService = new BookingServiceImpl(bookingRepository);
        BookingMapper bookingMapper = new BookingMapperImpl();
        BookingController controller = new BookingController(bookingService, bookingMapper);
        this.mockMvc = MockMvcBuilders
           //     .standaloneSetup(controller)
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void findAll_ShouldAddBookingEntriesToModelAndRenderBookingsListView() throws Exception {

        LocalDateTime ldt = LocalDateTime.now();

        Booking first = new Booking(1L,2L, ldt, 60L);

        when(bookingRepository.findAll()).thenReturn(Arrays.asList(first));
        when(bookingRepository.findOne(4L)).thenReturn(first);


        mockMvc.perform(get("/api/v1/bookings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/xml;charset=UTF-8"));

        verify(bookingRepository, times(1)).findAll();
        verifyNoMoreInteractions(bookingRepository);
    }

    @Test
    public void addBooking_shouldCreateNewBookingWithSecurityUser() throws Exception {

        mockMvc.perform(post("/api/v1/bookings").with(user("user1")).contentType("application/json").content(" {\n" +
                "    \"roomId\": 2,\n" +
                "    \"bookingTimeStart\": \"2011-01-24T22:08:41.079\",\n" +
                "    \"bookingDuration\": 60\n" +
                "  }"))
                .andExpect(status().isOk());

    }
}

