package org.comtravo.travel.controller;

import org.comtravo.travel.domain.dto.UserDto;
import org.comtravo.travel.domain.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import javax.validation.Valid;

@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService _userService) {
        userService = _userService;
    }

    @GetMapping("/users")
    public List<UserDto> Get(
        @RequestParam(value = "pageindex", defaultValue = "0", required = true) Integer pageIndex,
        @RequestParam(value = "pagesize", defaultValue = "25", required = true) Integer pageSize
        ) {

        var result = userService.Get(pageIndex, pageSize);
        return result;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void Post(@Valid @RequestBody UserDto user) {
        userService.Save(user);
    }    
}
