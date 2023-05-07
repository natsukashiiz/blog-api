package com.vv2dev.restfulapi.repository;

import com.vv2dev.restfulapi.entity.Blog;
import com.vv2dev.restfulapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByUser(User user);
}
