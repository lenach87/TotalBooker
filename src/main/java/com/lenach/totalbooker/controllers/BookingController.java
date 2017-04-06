package com.lenach.totalbooker.controllers;

import com.lenach.totalbooker.data.Booking;
import com.lenach.totalbooker.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by o.chubukina on 04/04/2017.
 */

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @RequestMapping(value="/allbookings", produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Booking> listAllBookingsWithMarshalling() {
        return bookingService.findAll();
    }

}
