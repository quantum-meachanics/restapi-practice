package com.ohgiraffers.restapipractice.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {

    private long id;
    private String title;
    private String content;
}
