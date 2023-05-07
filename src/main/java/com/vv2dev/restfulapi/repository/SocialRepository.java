package com.vv2dev.restfulapi.repository;

import com.vv2dev.restfulapi.entity.Social;
import com.vv2dev.restfulapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialRepository extends JpaRepository<Social, Long> {
    Optional<Social> findByUser(User user);
}
