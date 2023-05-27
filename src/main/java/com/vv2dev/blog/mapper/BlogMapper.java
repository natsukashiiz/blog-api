package com.vv2dev.blog.mapper;

import com.vv2dev.blog.entity.Blog;
import com.vv2dev.blog.model.MBlogResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogMapper {
    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);
    MBlogResponse toResponse(Blog blog);
}
