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

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Entity
@Table(name = "TBRooms")
public class Room implements Serializable {

    @Id
    @GeneratedValue
    @Column (name = "Room_ID")
    private long id;


    @Column (name = "Room_Name")
    private String name;

    public Room() {
    }

    public Room(long id, String name) {

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
