package com.tddproject.register.controller;

import com.tddproject.register.controller.mapper.UserMapper;
import com.tddproject.register.controller.request.UserRequest;
import com.tddproject.register.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserRequest request){
        userService.register(userMapper.toUser(request));
    }
}
