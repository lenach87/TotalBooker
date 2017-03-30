package com.lenach.totalbooker.repository;

import com.lenach.totalbooker.data.Room;
import com.lenach.totalbooker.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}