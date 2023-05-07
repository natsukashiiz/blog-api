package com.vv2dev.restfulapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "tbl_users")
@Data
public class User extends BaseEntity {
    @Column(nullable = false, unique = true, length = 60)
    private String email;
    @Column(nullable = false, length = 120)
    private String password;
    @Column(nullable = false, length = 30)
    private String name;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Social social;

    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Blog> blogs;
}
