package com.vv2dev.blog.repository;

import com.vv2dev.blog.entity.Blog;
import com.vv2dev.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByUser(User user);
}
