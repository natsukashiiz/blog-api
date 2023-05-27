package com.vv2dev.blog.business;

import com.vv2dev.blog.entity.Blog;
import com.vv2dev.blog.entity.User;
import com.vv2dev.blog.exception.BaseException;
import com.vv2dev.blog.exception.BlogException;
import com.vv2dev.blog.mapper.BlogMapper;
import com.vv2dev.blog.model.MBlogRequest;
import com.vv2dev.blog.model.MBlogResponse;
import com.vv2dev.blog.service.BlogService;
import com.vv2dev.blog.service.UserService;
import com.vv2dev.blog.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class BlogBusiness {
    private final BlogService blogService;
    private final UserService userService;

    public BlogBusiness(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    public MBlogResponse getById(Long id) throws BaseException {
        Optional<Blog> opt = blogService.findById(id);
        if (opt.isEmpty()) {
            throw BlogException.notFound();
        }

        return BlogMapper.INSTANCE.toResponse(opt.get());
    }

    public MBlogResponse create(MBlogRequest request) throws BaseException {
        Optional<Long> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw BlogException.unauthorized();
        }

        Long userId = opt.get();

        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()) {
            throw BlogException.notFound();
        }

        User user = optUser.get();
        Blog blog = blogService.create(user, request.getTitle(), request.getContent());
        return BlogMapper.INSTANCE.toResponse(blog);
    }

    public MBlogResponse updateById(Long id, MBlogRequest blog) throws BaseException {
        Optional<Blog> opt = blogService.findById(id);
        if (opt.isEmpty()) {
            throw BlogException.notFound();
        }

        Blog entity = opt.get();

        if (Objects.nonNull(blog.getTitle())) {
            entity.setTitle(blog.getTitle());
        }

        if (Objects.nonNull(blog.getContent())) {
            entity.setContent(blog.getContent());
        }

        Blog b = blogService.update(entity);

        return BlogMapper.INSTANCE.toResponse(b);
    }

    public void deleteById(Long id) throws BaseException {
        Optional<Blog> opt = blogService.findById(id);
        if (opt.isEmpty()) {
            throw BlogException.notFound();
        }

        blogService.deleteById(id);

        Optional<Blog> optDel = blogService.findById(id);
        if (optDel.isPresent()) {
            throw BlogException.deleteFail();
        }
    }
}
