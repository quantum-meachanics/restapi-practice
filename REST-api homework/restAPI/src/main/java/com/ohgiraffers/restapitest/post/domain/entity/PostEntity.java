package com.ohgiraffers.restapitest.post.domain.entity;

import com.ohgiraffers.restapitest.comment.domain.entity.CommentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "post")
@Table(name = "Post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> comment;
}
