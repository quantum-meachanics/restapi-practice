package com.ohgiraffers.restapitest.comment.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ohgiraffers.restapitest.post.domain.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "comment")
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private PostEntity postId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "create_date")
    @CreatedDate
    private LocalDateTime createDate;
}
