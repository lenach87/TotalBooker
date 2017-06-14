package com.lenach.totalbooker.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lenach.totalbooker.data.serializer.LocalDateTimeDeserializer;
import com.lenach.totalbooker.data.serializer.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by o.chubukina on 30/03/2017.
 */

@Entity
@Table(name = "tb_bookings")
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "booking_id")
    private long id;

    @Column(name = "room_id")
    private long roomId;

    @Column(name = "user_id")
    private long userId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "start_time")
    private LocalDateTime bookingTimeStart;

    @Column(name = "duration")
    private Long bookingDuration;


    public Booking() {
    }

    public Booking(long room_id, long user_id, LocalDateTime bookingTimeStart, Long bookingDuration) {

        this.roomId = room_id;
        this.userId = user_id;
        this.bookingTimeStart = bookingTimeStart;
        this.bookingDuration = bookingDuration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getBookingTimeStart() {
        return bookingTimeStart;
    }

    public void setBookingTimeStart(LocalDateTime bookingTimeStart) {
        this.bookingTimeStart = bookingTimeStart;
    }

    public Long getBookingDuration() {
        return bookingDuration;
    }

    public void setBookingDuration(Long bookingDuration) {
        this.bookingDuration = bookingDuration;
    }
}
