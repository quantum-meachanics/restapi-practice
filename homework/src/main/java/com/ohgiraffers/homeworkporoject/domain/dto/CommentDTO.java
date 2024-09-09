package com.ohgiraffers.homeworkporoject.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class CommentDTO {
    private Long id;
    private String text;
    private Long postId;


}
