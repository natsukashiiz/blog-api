package com.vv2dev.blog.api;

import com.vv2dev.blog.business.UserBusiness;
import com.vv2dev.blog.exception.BaseException;
import com.vv2dev.blog.model.MLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthApi {
    private final UserBusiness business;

    public AuthApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MLoginRequest request) throws BaseException {
        String response = business.login(request);
        return ResponseEntity.ok(response);
    }

//    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/refresh")
    public ResponseEntity<String> refreshToken() throws BaseException {
        String response = business.refreshToken();
        return ResponseEntity.ok(response);
    }
}
