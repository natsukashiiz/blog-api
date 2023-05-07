package com.vv2dev.restfulapi.model;

import lombok.Data;

@Data
public class MLoginRequest {
    private String email;
    private String password;
}
