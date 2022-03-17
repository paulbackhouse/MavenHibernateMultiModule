package org.comtravo.travel.domain.repository;

import java.util.List;

import org.comtravo.travel.domain.aggregates.UserBookingsAggregate;
import org.comtravo.travel.domain.entities.BookingEntity;

public interface IBookingRepository {
    
    /**
     * @summary             Gets a collection of bookings from the data store
     * @param pageIndex     A value which indicates what index of the entire db collection to return (zero based index)
     * @param pageSize      A value whichi describes the size of the collection to return for the given pageIndex
     * @return              A collection of bookings
     */
    public List<BookingEntity> Get(int pageIndex, int pageSize);

    /** 
     * @summary         Returns bookings associated to the given user by a given user id
     * @param userId    The PK Id of the user to get bookings for
    */
    public UserBookingsAggregate GetByUserId(int userId);
    
}
