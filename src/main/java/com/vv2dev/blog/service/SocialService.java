package com.vv2dev.blog.service;

import com.vv2dev.blog.repository.SocialRepository;
import com.vv2dev.blog.entity.Social;
import com.vv2dev.blog.entity.User;
import com.vv2dev.blog.exception.BaseException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialService {
    private final SocialRepository repository;

    public SocialService(SocialRepository repository) {
        this.repository = repository;
    }

    public Optional<Social> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Social create(User user, String facebook, String line, String instagram, String tiktok) throws BaseException {
        // TODO: validate

        // save
        Social entity = new Social();
        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTiktok(tiktok);

        return repository.save(entity);
    }
}
