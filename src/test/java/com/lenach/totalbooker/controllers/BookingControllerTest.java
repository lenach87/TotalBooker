package com.lenach.totalbooker.controllers;

import com.lenach.totalbooker.config.AppConfig;
import com.lenach.totalbooker.config.DataConfig;
import com.lenach.totalbooker.config.JPAConfig;
import com.lenach.totalbooker.config.WebConfig;
import com.lenach.totalbooker.controllers.mappers.BookingMapper;
import com.lenach.totalbooker.controllers.mappers.BookingMapperImpl;
import com.lenach.totalbooker.data.Booking;
import com.lenach.totalbooker.repository.BookingRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Created by o.chubukina on 10/05/2017.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class, WebConfig.class})
@Import(BookingMapperImpl.class)
public class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    BookingRepository bookingRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        BookingService bookingService = new BookingServiceImpl(bookingRepository);
        BookingMapper bookingMapper = new BookingMapperImpl();
        BookingController controller = new BookingController(bookingService, bookingMapper);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void findAll_ShouldAddBookingEntriesToModelAndRenderBookingsListView() throws Exception {

        LocalDateTime ldt = LocalDateTime.now();

        Booking first = new Booking(4L, 1L,2L, ldt, 60L);

        when(bookingRepository.findAll()).thenReturn(Arrays.asList(first));
        when(bookingRepository.findOne(4L)).thenReturn(first);


        mockMvc.perform(get("/booking-management/bookings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        verify(bookingRepository, times(1)).findAll();
        verifyNoMoreInteractions(bookingRepository);
    }
}

