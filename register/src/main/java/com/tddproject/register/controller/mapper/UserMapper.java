package com.tddproject.register.controller.mapper;

import com.tddproject.register.controller.request.UserRequest;
import com.tddproject.register.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toUser(UserRequest request);
}
