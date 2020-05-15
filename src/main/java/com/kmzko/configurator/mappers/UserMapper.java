package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.user.UserDto;
import com.kmzko.configurator.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {
    UserMapper() {
        super(User.class, UserDto.class);
    }
}
