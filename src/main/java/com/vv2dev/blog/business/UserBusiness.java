package com.vv2dev.blog.business;

import com.vv2dev.blog.entity.User;
import com.vv2dev.blog.exception.BaseException;
import com.vv2dev.blog.exception.UserException;
import com.vv2dev.blog.mapper.UserMapper;
import com.vv2dev.blog.model.MLoginRequest;
import com.vv2dev.blog.model.MRegisterRequest;
import com.vv2dev.blog.model.MRegisterResponse;
import com.vv2dev.blog.service.TokenService;
import com.vv2dev.blog.service.UserService;
import com.vv2dev.blog.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserBusiness {
    private final UserService userService;
    private final TokenService tokenService;

    public UserBusiness(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public User getById(Long id) throws BaseException {
        Optional<User> opt = userService.findById(id);
        if (opt.isEmpty()) {
            throw UserException.notFound();
        }

        return opt.get();
    }

    public User updateById(Long id, User user) throws BaseException {
        Optional<User> opt = userService.findById(id);
        if (opt.isEmpty()) {
            throw UserException.notFound();
        }

        User entity = opt.get();

        if (Objects.nonNull(user.getName())) {
            entity.setName(user.getName());
        }

        return userService.update(user);
    }

    public void deleteById(Long id) throws BaseException {
        Optional<User> opt = userService.findById(id);
        if (opt.isEmpty()) {
            throw UserException.notFound();
        }

        userService.deleteById(id);

        Optional<User> optDel = userService.findById(id);
        if (optDel.isPresent()) {
            throw UserException.deleteFail();
        }
    }

    public String refreshToken() throws BaseException {
        Optional<Long> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        Long userId = opt.get();

        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()) {
            throw UserException.notFound();
        }

        User user = optUser.get();
        return tokenService.tokenize(user);
    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        // TODO: mapper
        return UserMapper.INSTANCE.toRegisterResponse(user);
    }

    public String login(MLoginRequest request) throws BaseException {
        // validate request

        // validate database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if (opt.isEmpty()) {
            throw UserException.loginFailEmailNotFound();
        }

        User user = opt.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
            throw UserException.loginFailPasswordIncorrect();
        }

        // TODO: generate JWT
        return tokenService.tokenize(user);
    }
}
