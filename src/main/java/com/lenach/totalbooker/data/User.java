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
    private String name;

    public User() {
    }

    public User(long id, String name) {

        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
