package com.vv2dev.blog.repository;

import com.vv2dev.blog.entity.Social;
import com.vv2dev.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialRepository extends JpaRepository<Social, Long> {
    Optional<Social> findByUser(User user);
}
