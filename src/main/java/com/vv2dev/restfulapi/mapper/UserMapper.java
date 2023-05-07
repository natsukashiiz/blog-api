package com.vv2dev.restfulapi.mapper;

import com.vv2dev.restfulapi.model.MRegisterResponse;
import com.vv2dev.restfulapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    MRegisterResponse toRegisterResponse(User user);
}
