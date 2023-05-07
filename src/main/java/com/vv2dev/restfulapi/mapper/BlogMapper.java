package com.vv2dev.restfulapi.mapper;

import com.vv2dev.restfulapi.entity.Blog;
import com.vv2dev.restfulapi.entity.User;
import com.vv2dev.restfulapi.model.MBlogResponse;
import com.vv2dev.restfulapi.model.MRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogMapper {
    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);
    MBlogResponse toResponse(Blog blog);
}
