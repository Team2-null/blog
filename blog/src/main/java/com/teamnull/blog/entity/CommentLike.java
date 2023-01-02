package com.teamnull.blog.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    public CommentLike(Comment comment, User user, boolean islike) {
        this.comment = comment;
        this.user = user;
    }
}
