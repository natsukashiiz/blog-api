package com.vv2dev.restfulapi.model;

import lombok.Data;

@Data
public class MRegisterRequest {
    private String name;
    private String email;
    private String password;
}
