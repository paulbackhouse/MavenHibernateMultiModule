package org.comtravo.travel.domain.services;

import org.comtravo.travel.domain.aggregates.UserBookingsAggregate;

public interface IBookingService {
    
    public UserBookingsAggregate GetByUserId(int userId);

}
