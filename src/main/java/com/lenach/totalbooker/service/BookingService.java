package com.lenach.totalbooker.service;

import com.lenach.totalbooker.data.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by o.chubukina on 06/04/2017.
 */
@Service
public interface BookingService {

    Booking save (Booking booking);

    List<Booking> findAll();

    Booking findOne(long id);

    void delete(long id);
}
