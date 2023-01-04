package com.teamnull.blog.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @OneToMany
    // @JoinColumn(name = "post_id", nullable = false)
    // private Post post;

    // @OneToOne
    // @JoinColumn(name = "user_id", nullable = false)
    // private User user;

    // public PostLike(Post post, User user, boolean like) {
    //     this.post = post;
    //     this.user = user;
    // }
}
