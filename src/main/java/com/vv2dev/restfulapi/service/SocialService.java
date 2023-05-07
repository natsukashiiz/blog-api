package com.vv2dev.restfulapi.service;

import com.vv2dev.restfulapi.entity.Social;
import com.vv2dev.restfulapi.entity.User;
import com.vv2dev.restfulapi.exception.BaseException;
import com.vv2dev.restfulapi.repository.SocialRepository;
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
