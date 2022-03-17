package org.comtravo.travel.domain.repository;

import java.util.List;

import org.comtravo.travel.domain.entities.UserEntity;

public interface IUserRepository {

    /**
     * @summary             Gets a collection of users from the data store
     * @param pageIndex     A value which indicates what index of the entire db collection to return (zero based index)
     * @param pageSize      A value whichi describes the size of the collection to return for the given pageIndex
     * @return              A collection of users
    */
    public List<UserEntity> Get(int pageIndex, int pageSize);

    /**
     * @summary        Saves (Upsert logic) a user record, updates the modified date. When 'Id' is null or 0, is considered a new record
     * @param user     The entity to save 
     */
    public void Save(UserEntity user);

}
