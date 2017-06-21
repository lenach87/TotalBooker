package com.lenach.totalbooker.controllers.viewmodel;

/**
 * Created by o.chubukina on 05/05/2017.
 */
public class BookingViewModel {

    private long id;
    private long roomId;
    private long userId;
    private String bookingTimeStart;
    private String bookingDuration;

    public BookingViewModel(long roomId, long userId, String bookingTimeStart, String bookingDuration) {
        this.roomId = roomId;
        this.userId = userId;
        this.bookingTimeStart = bookingTimeStart;
        this.bookingDuration = bookingDuration;
    }

    public BookingViewModel() {
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
}
