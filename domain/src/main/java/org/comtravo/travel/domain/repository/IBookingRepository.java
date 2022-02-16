package org.comtravo.travel.domain.repository;

import org.comtravo.travel.domain.aggregates.UserBookingsAggregate;

public interface IBookingRepository {
    
    public UserBookingsAggregate GetByUserId(int userId);
    
}
