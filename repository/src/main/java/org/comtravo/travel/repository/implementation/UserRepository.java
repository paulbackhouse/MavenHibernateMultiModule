package org.comtravo.travel.repository.implementation;

import java.util.List;

import org.comtravo.travel.domain.entities.UserEntity;
import org.comtravo.travel.domain.repository.IUserRepository;
import org.comtravo.travel.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository implements IUserRepository {

    @Override
    public List<UserEntity> Get(int pageIndex, int pageSize) {
        
         return UsingDbWithResult(db -> {
            var results = findAll(UserEntity.class, db, pageIndex, pageSize);
            return results;
        });
    }

    @Override
    public void Save(UserEntity user) {

        var ts = GetTimestamp();
        user.setModified(ts);

        if (user.getId() == null || user.getId() == 0) {
            user.setCreated(ts);
        }

        if (IsValid(user)) {
            
            UsingDbWithTransaction(db -> {
                db.saveOrUpdate(user);
            });
        }
    }
}
