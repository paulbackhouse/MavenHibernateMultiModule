package org.comtravo.travel.controller;

import org.comtravo.travel.domain.aggregates.UserBookingsAggregate;
import org.comtravo.travel.domain.dto.UserDto;
import org.comtravo.travel.domain.services.IBookingService;
import org.comtravo.travel.domain.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
public class UserController {

    private final IUserService userService;
    private final IBookingService bookingService;

    @Autowired
    public UserController(
        IUserService userService,
        IBookingService bookingService
        ) {
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping("/users")
    public List<UserDto> Get(
        @RequestParam(value = "pageindex", defaultValue = "0", required = true) Integer pageIndex,
        @RequestParam(value = "pagesize", defaultValue = "25", required = true) Integer pageSize
        ) {

        var result = userService.Get(pageIndex, pageSize);
        return result;
    }

    @GetMapping("/users/{id}/bookings")
    public UserBookingsAggregate GetUserBookings(@PathVariable("id") int id) {
        return bookingService.GetByUserId(id);
    }    

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void Post(@Valid @RequestBody UserDto user) {
        userService.Save(user);
    }    
}
