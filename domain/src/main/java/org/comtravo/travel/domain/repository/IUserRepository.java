package org.comtravo.travel.domain.repository;

import java.util.List;

import org.comtravo.travel.domain.entities.UserEntity;

public interface IUserRepository {

    public List<UserEntity> Get(int pageIndex, int pageSize);

    public void Save(UserEntity user);

}
