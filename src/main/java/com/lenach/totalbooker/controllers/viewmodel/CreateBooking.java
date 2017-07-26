package com.lenach.totalbooker.controllers.viewmodel;

/**
 * Created by o.chubukina on 14/06/2017.
 */
public class CreateBooking {

    private long roomId;
    private String bookingTimeStart;
    private String bookingDuration;

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getBookingTimeStart() {
        return bookingTimeStart;
    }

    public void setBookingTimeStart(String bookingTimeStart) {
        this.bookingTimeStart = bookingTimeStart;
    }

    public String getBookingDuration() {
        return bookingDuration;
    }

    public void setBookingDuration(String bookingDuration) {
        this.bookingDuration = bookingDuration;
    }

    public CreateBooking(long roomId, String bookingTimeStart, String bookingDuration) {
        this.roomId = roomId;
        this.bookingTimeStart = bookingTimeStart;
        this.bookingDuration = bookingDuration;
    }

    public CreateBooking() {
    }
}
