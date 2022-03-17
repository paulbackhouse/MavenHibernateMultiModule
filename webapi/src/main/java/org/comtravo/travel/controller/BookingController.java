package org.comtravo.travel.controller;

import java.util.List;

import org.comtravo.travel.domain.dto.BookingDto;
import org.comtravo.travel.domain.services.IBookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {
    
    private final IBookingService bookingService;

    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<BookingDto> Get(
        @RequestParam(value = "pageindex", defaultValue = "0", required = true) Integer pageIndex,
        @RequestParam(value = "pagesize", defaultValue = "25", required = true) Integer pageSize        
    ) {
        var bookings = bookingService.Get(pageIndex, pageSize);
        return bookings;
    }


}
