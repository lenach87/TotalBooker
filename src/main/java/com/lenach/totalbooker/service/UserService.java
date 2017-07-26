package com.lenach.totalbooker.service;

import com.lenach.totalbooker.data.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by o.chubukina on 06/04/2017.
 */

@Service
public interface UserService {
    User save (User user);

    List<User> findAll();

    User findOne(long id);

    User findUserByUsername (String username);

    void delete(long id);
}
