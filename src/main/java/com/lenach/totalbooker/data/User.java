package com.lenach.totalbooker.data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Entity
@Table(name = "tb_users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column (name = "user_id")
    private long id;


    @Column (name = "user_name")
    private String username;

    @Column (name = "password")
    private String password;

    public User() {
    }

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
