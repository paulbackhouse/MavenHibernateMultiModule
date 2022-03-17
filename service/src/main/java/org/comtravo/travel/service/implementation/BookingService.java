package org.comtravo.travel.service.implementation;

import java.util.List;

import org.comtravo.travel.domain.aggregates.UserBookingsAggregate;
import org.comtravo.travel.domain.dto.BookingDto;
import org.comtravo.travel.domain.repository.IBookingRepository;
import org.comtravo.travel.domain.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService extends BaseService implements IBookingService {

    private final IBookingRepository bookingRepository;

    @Autowired
    public BookingService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingDto> Get(int pageIndex, int pageSize) {

        var results = bookingRepository.Get(pageIndex, pageSize);
        var bookings = MapToList(results, BookingDto.class);
        return bookings;
    }
    
    @Override
    public UserBookingsAggregate GetByUserId(int userId) {
        return bookingRepository.GetByUserId(userId);
    }
    
}
