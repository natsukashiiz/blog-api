package com.vv2dev.blog.api;

import com.vv2dev.blog.business.UserBusiness;
import com.vv2dev.blog.entity.User;
import com.vv2dev.blog.exception.BaseException;
import com.vv2dev.blog.model.MRegisterRequest;
import com.vv2dev.blog.model.MRegisterResponse;
import com.vv2dev.blog.repository.UserRepository;
import com.vv2dev.blog.response.ResponseUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserApi {
    private final UserRepository repository;
    private final UserBusiness business;

    public UserApi(UserRepository repository, UserBusiness business) {
        this.repository = repository;
        this.business = business;
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sort,
            @RequestParam(required = false, defaultValue = "id") String sortFields,
            HttpServletRequest request) {

        int total = repository.findAll().toArray().length;
        Page<User> data = repository.findAll(PageRequest.of(page - 1, limit, Sort.by(sort, sortFields)));
        return ResponseUtil.page(data, total, sort, sortFields, request);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws BaseException {
        User response = business.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
        MRegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<?> editAllById(@PathVariable Long id, @RequestBody User request) throws BaseException {
        User response = business.updateById(id, request);
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{id}")
    public ResponseEntity<?> editById(@PathVariable Long id, @RequestBody User request) throws BaseException {
        User response = business.updateById(id, request);
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws BaseException {
        business.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
