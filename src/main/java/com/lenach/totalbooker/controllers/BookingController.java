package com.lenach.totalbooker.controllers;

import com.lenach.totalbooker.controllers.mappers.BookingMapper;
import com.lenach.totalbooker.controllers.viewmodel.BookingViewModel;
import com.lenach.totalbooker.controllers.viewmodel.CreateBooking;
import com.lenach.totalbooker.data.Booking;
import com.lenach.totalbooker.data.User;
import com.lenach.totalbooker.security.UserAccount;
import com.lenach.totalbooker.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by o.chubukina on 04/04/2017.
 */

@Controller
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingController(BookingService bookingService, BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper=bookingMapper;
    }

    @RequestMapping(produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Booking> listAllBookings() {
        return bookingService.findAll();
    }

    @RequestMapping(value="/{id}", produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BookingViewModel bookingById(@PathVariable(value = "id") long id) {
        return bookingMapper.bookingToBookingModel(bookingService.findOne(id));
    }

    @RequestMapping(consumes={"application/xml", "application/json"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Booking createNewBooking(@AuthenticationPrincipal UserAccount activeUser, @RequestBody CreateBooking model) {
        BookingViewModel booking = new BookingViewModel(model.getRoomId(), activeUser.getId(), model.getBookingTimeStart(), model.getBookingDuration());
        return bookingService.save(bookingMapper.bookingFromBookingModel(booking));
    }
}
