package com.ohgiraffers.homeworkporoject.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class PostDTO {
    private Long id;
    private String title;
    private String content;
}
