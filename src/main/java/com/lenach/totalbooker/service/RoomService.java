package com.lenach.totalbooker.service;

import com.lenach.totalbooker.repository.RoomRepository;
import com.lenach.totalbooker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Service
public class RoomService {


    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

}

