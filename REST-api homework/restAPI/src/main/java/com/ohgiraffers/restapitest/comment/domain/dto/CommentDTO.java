package com.ohgiraffers.restapitest.comment.domain.dto;

import com.ohgiraffers.restapitest.post.domain.entity.PostEntity;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO {

    private int commentId;
    private PostEntity postId;
    private String content;
    private Date createDate;

}
