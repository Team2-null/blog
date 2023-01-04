package com.teamnull.blog.entity;

import com.teamnull.blog.entity.enums.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private final List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Likes> commentlikes = new ArrayList<>();

    public User(String username, String password, UserRoleEnum role) {

        if (username == null) {
            throw new IllegalArgumentException("저장할 수 있는 사용자명이 없습니다.");
        }

        if (password == null) {
            throw new IllegalArgumentException("저장할 수 있는 패스워드가 없습니다.");
        }

        if (role == null) {
            throw new IllegalArgumentException("저장할 수 있는 역할이 없습니다.");
        }

        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean isAdmin() {
        return this.role == UserRoleEnum.ADMIN;
    }

}
