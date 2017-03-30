package com.lenach.totalbooker.data;

import java.io.Serializable;

/**
 * Created by o.chubukina on 30/03/2017.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TBUsers")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column (name = "User_ID")
    private long id;


    @Column (name = "User_Name")
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
