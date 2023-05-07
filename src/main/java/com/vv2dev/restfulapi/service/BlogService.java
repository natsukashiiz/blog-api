package com.vv2dev.restfulapi.service;

import com.vv2dev.restfulapi.entity.Blog;
import com.vv2dev.restfulapi.entity.User;
import com.vv2dev.restfulapi.exception.BaseException;
import com.vv2dev.restfulapi.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository repository;

    public BlogService(BlogRepository repository) {
        this.repository = repository;
    }


    public Optional<Blog> findById(Long id) {
        return repository.findById(id);
    }

    public List<Blog> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Blog create(User user, String title, String content) throws BaseException {
        // TODO: validate

        // save
        Blog entity = new Blog();
        entity.setUser(user);
        entity.setTitle(title);
        entity.setContent(content);

        return repository.save(entity);
    }

    public Blog update(Blog blog) {
        return repository.save(blog);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
