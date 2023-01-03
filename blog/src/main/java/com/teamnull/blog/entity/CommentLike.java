package com.teamnull.blog.entity;

import javax.persistence.Column;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Column(nullable = false)
    // boolean isliked = false;
    // @Column(nullable = false)
    // boolean isdisliked = false;
}
