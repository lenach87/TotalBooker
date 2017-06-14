package com.lenach.totalbooker.security;

import com.lenach.totalbooker.data.User;
import com.lenach.totalbooker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by o.chubukina on 13/06/2017.
 */
@Transactional(readOnly=true)
@Service("bookingUserDetailsService")
public class BookingUserDetailsService implements UserDetailsService {


    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserAccount account = new UserAccount (userService.findUserByUsername(username));

        if(account==null) {throw new UsernameNotFoundException("No such user: " + username);
        }

        return account;
    }



}