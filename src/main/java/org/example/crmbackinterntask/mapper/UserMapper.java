package org.example.crmbackinterntask.mapper;

import org.example.crmbackinterntask.entity.User;
import org.example.crmbackinterntask.model.request.UserRequest;
import org.example.crmbackinterntask.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserResponse toUserDto(User user);

    User toUser(UserRequest userRequest);

}
