package com.lenach.totalbooker.service;


import com.lenach.totalbooker.data.Room;
import com.lenach.totalbooker.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl (RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findOne(long id) {
        return roomRepository.findOne(id);
    }

    @Override
    public void delete(long id) {
        roomRepository.delete(id);
    }
}

