package org.comtravo.travel.domain.aggregates;

import java.util.List;

import org.comtravo.travel.domain.entities.BookingEntity;
import org.comtravo.travel.domain.entities.UserEntity;

public class UserBookingsAggregate extends UserEntity {
    
    public UserBookingsAggregate(
        UserEntity user, List<BookingEntity> bookings
    ) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setGivenName(user.getGivenName());
        this.setLastName(user.getLastName());
        this.setCreated(user.getCreated());
        this.setModified(user.getModified());
        this.setBookings(bookings);
    }

    private List<BookingEntity> Bookings;

    public List<BookingEntity> getBookings() {
        return Bookings;
    }

    public void setBookings(List<BookingEntity> bookings) {
        Bookings = bookings;
    }
}
