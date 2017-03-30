package com.lenach.totalbooker.service;

import com.lenach.totalbooker.repository.RoomRepository;
import com.lenach.totalbooker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Service
public class UserService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

}

