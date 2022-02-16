package org.comtravo.travel.service.implementation;

import org.comtravo.travel.domain.aggregates.UserBookingsAggregate;
import org.comtravo.travel.domain.repository.IBookingRepository;
import org.comtravo.travel.domain.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService {

    private final IBookingRepository bookingRepository;

    @Autowired
    public BookingService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public UserBookingsAggregate GetByUserId(int userId) {
        return bookingRepository.GetByUserId(userId);
    }
    
}
