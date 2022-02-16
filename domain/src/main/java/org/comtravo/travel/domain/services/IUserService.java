package org.comtravo.travel.domain.services;

import org.comtravo.travel.domain.dto.UserDto;

import java.util.List;

public interface IUserService {

    public List<UserDto> Get(int pageIndex, int pageSize);

    public void Save(UserDto user);

}
