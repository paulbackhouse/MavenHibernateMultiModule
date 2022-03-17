package org.comtravo.travel.repository.implementation;

import java.util.List;

import org.comtravo.travel.domain.aggregates.UserBookingsAggregate;
import org.comtravo.travel.domain.entities.BookingEntity;
import org.comtravo.travel.domain.entities.QBookingEntity;
import org.comtravo.travel.domain.entities.UserEntity;
import org.comtravo.travel.domain.repository.IBookingRepository;
import org.comtravo.travel.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRespository extends BaseRepository implements IBookingRepository {

    public List<BookingEntity> Get(int pageIndex, int pageSize) {
        return UsingDbWithResult(db -> {

            // queryDSL paging example
            var booking = GetQueryEntity();
            var query = CreateQuery(db);
            var lst = query
                        .from(booking)
                        .orderBy(booking.Created.desc())
                        .offset(pageIndex * pageSize)
                        .limit(pageSize)
                        .list(booking);

            return lst;
        });        
    }

    public UserBookingsAggregate GetByUserId(int userId) {

        return UsingDbWithResult(db -> {

            // simple Hibernate query
            var user = (UserEntity) db.get(UserEntity.class, userId);

            if (user == null) {
                throw new IllegalArgumentException("User record not found for userId "+ userId);
            }

            // queryDsl example
            var booking = GetQueryEntity();
            var query = CreateQuery(db);
            var lst = query
                        .from(booking)
                        .where(booking.UserId.eq(userId))
                        .list(booking);
                
            return new UserBookingsAggregate(user, lst); 
        });
    }


    private QBookingEntity GetQueryEntity() {
        return QBookingEntity.bookingEntity;
    }

}
