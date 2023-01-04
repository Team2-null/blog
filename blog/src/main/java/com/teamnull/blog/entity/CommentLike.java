package com.teamnull.blog.entity;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long commentId;

    public CommentLike(User user, Long postId, Long commentId) {
        this.user = user;
        this.postId = postId;
        this.commentId = commentId;
    }
}
