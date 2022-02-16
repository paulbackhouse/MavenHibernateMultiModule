package org.comtravo.travel.repository.implementation;

import org.comtravo.travel.domain.aggregates.UserBookingsAggregate;
import org.comtravo.travel.domain.entities.BookingEntity;
import org.comtravo.travel.domain.entities.UserEntity;
import org.comtravo.travel.domain.repository.IBookingRepository;
import org.comtravo.travel.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRespository extends BaseRepository implements IBookingRepository {

    public UserBookingsAggregate GetByUserId(int userId) {

        return UsingDbWithResult(db -> {

            var user = (UserEntity) db.get(UserEntity.class, userId);

            if (user == null) {
                throw new IllegalArgumentException("User record not found for userId "+ userId);
            }

            // note: this is hql
            // query the Class name an properties
            // this is mapped in the hibernate.cfg.xml file
            var query = db.createQuery("from BookingEntity b where b.UserId = :userId", BookingEntity.class);
            query.setParameter("userId", userId);
            var bookings = query.getResultList();
                
            return new UserBookingsAggregate(user, bookings); 
        });
    }


}
