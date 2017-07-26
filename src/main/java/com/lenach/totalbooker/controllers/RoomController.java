package com.lenach.totalbooker.controllers;


import com.lenach.totalbooker.data.Room;
import com.lenach.totalbooker.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by o.chubukina on 04/04/2017.
 */
@Controller
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @RequestMapping(produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Room> listAllRooms() {
        return roomService.findAll();
    }


    @RequestMapping(value="/{id}", produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Room roomById(@PathVariable(value = "id") long id) {
        return roomService.findOne(id);
    }
}
