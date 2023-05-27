package com.vv2dev.blog.service;

import com.vv2dev.blog.entity.Blog;
import com.vv2dev.blog.entity.User;
import com.vv2dev.blog.exception.BaseException;
import com.vv2dev.blog.repository.BlogRepository;
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
