package com.ohgiraffers.restapipractice.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreatePostRequest {

    private String title;
    private String content;
}
