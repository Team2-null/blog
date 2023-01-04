package com.teamnull.blog.entity;

import javax.persistence.*;

import com.teamnull.blog.entity.enums.LikeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private LikeEnum type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long targetId;

    public Likes(User user, LikeEnum type, Long targetId) {
        this.user = user;
        this.type = type;
        this.targetId = targetId;
    }
}
