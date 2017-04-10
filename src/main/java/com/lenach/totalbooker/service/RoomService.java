package com.lenach.totalbooker.service;

import com.lenach.totalbooker.data.Booking;
import com.lenach.totalbooker.data.Room;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by o.chubukina on 06/04/2017.
 */

@Service
public interface RoomService {

    Room save (Room room);

    List<Room> findAll();

    Room findOne(long id);

    void delete(long id);
}
