package com.lenach.totalbooker.service;

import com.lenach.totalbooker.data.Booking;
import com.lenach.totalbooker.data.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by o.chubukina on 06/04/2017.
 */

@Service
public interface UserService {
    User save (Booking device);

    List<User> findAll();

    User findOne(long id);

    void delete(long id);
}
