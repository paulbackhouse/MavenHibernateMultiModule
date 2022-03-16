package org.comtravo.travel.service.implementation;

import java.util.List;

import org.comtravo.travel.domain.dto.UserDto;
import org.comtravo.travel.domain.entities.UserEntity;
import org.comtravo.travel.domain.repository.IUserRepository;
import org.comtravo.travel.domain.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService extends BaseService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository _userRepository) {
        userRepository = _userRepository;
    }

    @Override
    public List<UserDto> Get(int pageIndex, int pageSize) {
        log.info("Getting users");

        var results = userRepository.Get(pageIndex, pageSize);
        var mapped = MapToList(results, UserDto.class);
        return mapped;
    }

    @Override
    public void Save(UserDto user) {
        log.info("Saving user");

        var entity = MapTo(user, UserEntity.class);
        userRepository.Save(entity);        
    }


}
