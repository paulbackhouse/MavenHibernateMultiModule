package org.comtravo.travel.repository.implementation;

import java.util.List;

import org.comtravo.travel.domain.entities.QUserEntity;
import org.comtravo.travel.domain.entities.UserEntity;
import org.comtravo.travel.domain.repository.IUserRepository;
import org.comtravo.travel.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository 
public class UserRepository extends BaseRepository implements IUserRepository {

    @Override
    public List<UserEntity> Get(int pageIndex, int pageSize) {
        
         return UsingDbWithResult(db -> {

            var user = GetQueryEntity();
            var query = CreateQuery(db);
            var lst = query
                        .from(user)
                        .orderBy(
                                user.LastName.asc(),
                                user.GivenName.asc()
                        )
                        .offset(pageIndex * pageSize)   
                        .limit(pageSize)
                        .list(user);  

            return lst;
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

    private QUserEntity GetQueryEntity() {
        return QUserEntity.userEntity;
    }    
}
