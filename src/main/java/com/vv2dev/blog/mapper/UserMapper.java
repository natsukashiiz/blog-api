package com.vv2dev.blog.mapper;

import com.vv2dev.blog.entity.User;
import com.vv2dev.blog.model.MRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    MRegisterResponse toRegisterResponse(User user);
}
