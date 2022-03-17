package org.comtravo.travel.domain.repository;

import org.comtravo.travel.domain.aggregates.UserBookingsAggregate;

public interface IBookingRepository {
    
    /** 
     * @summary Returns bookings associated to the given user by a given user id
    */
    public UserBookingsAggregate GetByUserId(int userId);
    
}
