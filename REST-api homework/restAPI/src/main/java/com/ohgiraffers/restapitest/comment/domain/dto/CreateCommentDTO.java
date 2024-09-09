package com.ohgiraffers.restapitest.comment.domain.dto;

import com.ohgiraffers.restapitest.post.domain.entity.PostEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateCommentDTO {

    private int postId;
    private String content;
}
