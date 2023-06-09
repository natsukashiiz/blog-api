package com.vv2dev.blog.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "tbl_blogs")
@Data
public class Blog extends BaseEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition="TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
