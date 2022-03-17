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

    public List<BookingEntity> Get() {
        return UsingDbWithResult(db -> {

            var bookings = GetQueryEntity();
            var query = CreateQuery(db);
            var lst = query
                        .from(bookings)
                        .list(bookings);

            return lst;
        });        
    }

    public UserBookingsAggregate GetByUserId(int userId) {

        return UsingDbWithResult(db -> {

            var user = (UserEntity) db.get(UserEntity.class, userId);

            if (user == null) {
                throw new IllegalArgumentException("User record not found for userId "+ userId);
            }

            var bookings = GetQueryEntity();
            var query = CreateQuery(db);
            var lst = query
                        .from(bookings)
                        .where(bookings.UserId.eq(userId))
                        .list(bookings);

            // note: this is hql
            // query the Class name an properties
            // this is mapped in the hibernate.cfg.xml file
            // var query = db.createQuery("from BookingEntity b where b.UserId = :userId", BookingEntity.class);
            // query.setParameter("userId", userId);
            // var bookings = query.getResultList();
                
            return new UserBookingsAggregate(user, lst); 
        });
    }


    private QBookingEntity GetQueryEntity() {
        return QBookingEntity.bookingEntity;
    }

}
