package org.comtravo.travel.service.implementation;

import org.comtravo.travel.domain.dto.UserDto;
import org.comtravo.travel.domain.entities.UserEntity;
import org.comtravo.travel.domain.repository.IUserRepository;
import org.comtravo.travel.domain.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository _userRepository) {
        userRepository = _userRepository;
    }

    @Override
    public List<UserDto> Get(int pageIndex, int pageSize) {
        logger.info("Getting users");

        var results = userRepository.Get(pageIndex, pageSize);
        var mapped = MapToList(results, UserDto.class);
        return mapped;
    }

    @Override
    public void Save(UserDto user) {
        logger.info("Saving user");

        var entity = MapTo(user, UserEntity.class);
        userRepository.Save(entity);        
    }


}
