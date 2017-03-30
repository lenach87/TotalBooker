package com.lenach.totalbooker.data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Entity
@Table(name = "Bookings")
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "Booking_ID")
    private long id;

    @Column(name = "Room_ID")
    private long room_id;

    @Column(name = "User_ID")
    private long user_id;

    @Column
    private LocalDateTime bookingTime;

    public Booking() {
    }

    public Booking(long room_id, long user_id, LocalDateTime bookingTime) {

        this.room_id = room_id;
        this.user_id = user_id;
        this.bookingTime = bookingTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}
