package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.BioDto;
import com.kmzko.configurator.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserBioMapper extends AbstractMapper<User, BioDto> {
    UserBioMapper() {
        super(User.class, BioDto.class);
    }
}
