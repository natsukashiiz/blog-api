package com.vv2dev.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "tbl_socials")
@Data
public class Social extends BaseEntity {
    @Column(length = 120)
    private String facebook;
    @Column(length = 120)
    private String line;
    @Column(length = 120)
    private String instagram;
    @Column(length = 120)
    private String tiktok;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
