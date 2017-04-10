package com.lenach.totalbooker.service;

import com.lenach.totalbooker.data.Booking;
import com.lenach.totalbooker.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;


    @Autowired
    public BookingServiceImpl (BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public Booking save (Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findOne(long id){
        return bookingRepository.findOne(id);
    }

    @Override
    public void delete(long id) {
        bookingRepository.delete(id);
    }
}

